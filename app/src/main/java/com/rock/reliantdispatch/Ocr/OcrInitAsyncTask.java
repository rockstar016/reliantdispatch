/*
 * Copyright 2011 Robert Theis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rock.reliantdispatch.Ocr;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Installs the language data required for OCR, and initializes the OCR engine using a background 
 * thread.
 */
final class OcrInitAsyncTask extends AsyncTask<String, String, Boolean> {
  private static final String TAG = OcrInitAsyncTask.class.getSimpleName();

  /** Suffixes of required data files for Cube. */
  private static final String[] CUBE_DATA_FILES = {
    ".cube.bigrams",
    ".cube.fold", 
    ".cube.lm", 
    ".cube.nn", 
    ".cube.params", 
    //".cube.size", // This file is not available for Hindi
    ".cube.word-freq", 
    ".tesseract_cube.nn", 
    ".traineddata"
  };

  private CaptureActivity activity;
  private Context context;
  private TessBaseAPI baseApi;
  private ProgressDialog dialog;
  private ProgressDialog indeterminateDialog;
  private final String languageCode;
  private String languageName;
  private int ocrEngineMode;

  OcrInitAsyncTask(CaptureActivity activity, TessBaseAPI baseApi, ProgressDialog dialog,
                   ProgressDialog indeterminateDialog, String languageCode, String languageName,
                   int ocrEngineMode) {
    this.activity = activity;
    this.context = activity.getBaseContext();
    this.baseApi = baseApi;
    this.dialog = dialog;
    this.indeterminateDialog = indeterminateDialog;
    this.languageCode = languageCode;
    this.languageName = languageName;
    this.ocrEngineMode = ocrEngineMode;
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    dialog.setTitle("Please wait");
    dialog.setMessage("Checking for data installation...");
    dialog.setIndeterminate(false);
    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    dialog.setCancelable(false);
    dialog.show();
    activity.setButtonVisibility(false);
  }

  protected Boolean doInBackground(String... params) {
    // Check whether we need Cube data or Tesseract data.
    // Example Cube data filename: "tesseract-ocr-3.01.eng.tar"
    // Example Tesseract data filename: "eng.traineddata"
    String destinationFilenameBase = languageCode + ".traineddata";
    boolean isCubeSupported = true;
    // Check for, and create if necessary, folder to hold model data
    String destinationDirBase = params[0]; // The storage directory, minus the
                                           // "tessdata" subdirectory
    File tessdataDir = new File(destinationDirBase + File.separator + "tessdata");
    if (!tessdataDir.exists() && !tessdataDir.mkdirs()) {
      Log.e(TAG, "Couldn't make directory " + tessdataDir);
      return false;
    }

    // Create a reference to the file to save the download in
    File downloadFile = new File(tessdataDir, destinationFilenameBase);

    // Check if an incomplete download is present. If a *.download file is there, delete it and
    // any (possibly half-unzipped) Tesseract and Cube data files that may be there.
    File incomplete = new File(tessdataDir, destinationFilenameBase + ".download");
    File tesseractTestFile = new File(tessdataDir, languageCode + ".traineddata");
    if (incomplete.exists()) {
      incomplete.delete();
      if (tesseractTestFile.exists()) {
        tesseractTestFile.delete();
      }
      deleteCubeDataFiles(tessdataDir);
    }

    // Check whether all Cube data files have already been installed
    boolean isAllCubeDataInstalled = false;
    boolean isAFileMissing = false;
    File dataFile;
    for (String s : CUBE_DATA_FILES) {
      dataFile = new File(tessdataDir.toString() + File.separator + languageCode + s);
      if (!dataFile.exists()) {
        isAFileMissing = true;
      }
    }
    isAllCubeDataInstalled = !isAFileMissing;


    // If language data files are not present, install them
    boolean installSuccess = false;
    if (!tesseractTestFile.exists()
        || (isCubeSupported && !isAllCubeDataInstalled)) {
      Log.d(TAG, "Language data for " + languageCode + " not found in " + tessdataDir.toString());
      deleteCubeDataFiles(tessdataDir);

      // Check assets for language data to install. If not present, download from Internet
      try {
        Log.d(TAG, "Checking for language data (" + destinationFilenameBase
            + ".zip) in application assets...");
        // Check for a file like "eng.traineddata.zip" or "tesseract-ocr-3.01.eng.tar.zip"
        installSuccess = installFromAssets(destinationFilenameBase + ".zip", tessdataDir, 
            downloadFile);
      } catch (IOException e) {
        Log.e(TAG, "IOException", e);
      } catch (Exception e) {
        Log.e(TAG, "Got exception", e);
      }

      if (!installSuccess) {
        // File was not packaged in assets, so download it
        Log.d(TAG, "Downloading " + destinationFilenameBase + ".gz...");
        try {
          installSuccess = downloadFile(destinationFilenameBase, downloadFile);
          if (!installSuccess) {
            Log.e(TAG, "Download failed");
            return false;
          }
        } catch (IOException e) {
          Log.e(TAG, "IOException received in doInBackground. Is a network connection available?");
          return false;
        }
      }

      // If we have a tar file at this point because we downloaded v3.01+ data, untar it
      String extension = destinationFilenameBase.substring(
          destinationFilenameBase.lastIndexOf('.'),
          destinationFilenameBase.length());
//      if (extension.equals(".tar")) {
//        try {
//          untar(new File(tessdataDir.toString() + File.separator + destinationFilenameBase),
//              tessdataDir);
//          installSuccess = true;
//        } catch (IOException e) {
//          Log.e(TAG, "Untar failed");
//          return false;
//        }
//      }

    } else {
      Log.d(TAG, "Language data for " + languageCode + " already installed in "
          + tessdataDir.toString());
      installSuccess = true;
    }

    // If OSD data file is not present, download it
    File osdFile = new File(tessdataDir, CaptureActivity.OSD_FILENAME_BASE);
    boolean osdInstallSuccess = false;
    if (!osdFile.exists()) {
      // Check assets for language data to install. If not present, download from Internet
      languageName = "orientation and script detection";
      try {
        // Check for, and delete, partially-downloaded OSD files
        String[] badFiles = { CaptureActivity.OSD_FILENAME + ".gz.download",
            CaptureActivity.OSD_FILENAME + ".gz", CaptureActivity.OSD_FILENAME };
        for (String filename : badFiles) {
          File file = new File(tessdataDir, filename);
          if (file.exists()) {
            file.delete();
          }
        }
        
        Log.d(TAG, "Checking for OSD data (" + CaptureActivity.OSD_FILENAME_BASE
            + ".zip) in application assets...");
        // Check for "osd.traineddata.zip"
        osdInstallSuccess = installFromAssets(CaptureActivity.OSD_FILENAME_BASE + ".zip", 
            tessdataDir, new File(CaptureActivity.OSD_FILENAME));
      } catch (IOException e) {
        Log.e(TAG, "IOException", e);
      } catch (Exception e) {
        Log.e(TAG, "Got exception", e);
      }

      if (!osdInstallSuccess) {
        // File was not packaged in assets, so download it
        Log.d(TAG, "Downloading " + CaptureActivity.OSD_FILENAME + ".gz...");
        try {
          osdInstallSuccess = downloadFile(CaptureActivity.OSD_FILENAME, new File(tessdataDir,
              CaptureActivity.OSD_FILENAME));
          if (!osdInstallSuccess) {
            Log.e(TAG, "Download failed");
            return false;
          }
        } catch (IOException e) {
          Log.e(TAG, "IOException received in doInBackground. Is a network connection available?");
          return false;
        }
      }

    } else {
      Log.d(TAG, "OSD file already present in " + tessdataDir.toString());
      osdInstallSuccess = true;
    }
    
    // Dismiss the progress dialog box, revealing the indeterminate dialog box behind it
    try {
      dialog.dismiss();
    } catch (IllegalArgumentException e) {
      // Catch "View not attached to window manager" error, and continue
    }

    // Initialize the OCR engine
    if (baseApi.init(destinationDirBase + File.separator, languageCode, ocrEngineMode)) {
      return installSuccess && osdInstallSuccess;
    }
    return false;
  }

  private void deleteCubeDataFiles(File tessdataDir) {
    File badFile;
    for (String s : CUBE_DATA_FILES) {
      badFile = new File(tessdataDir.toString() + File.separator + languageCode + s);
      if (badFile.exists()) {
        Log.d(TAG, "Deleting existing file " + badFile.toString());
        badFile.delete();
      }
      badFile = new File(tessdataDir.toString() + File.separator + "tesseract-ocr-3.01."
          + languageCode + ".tar");
      if (badFile.exists()) {
        Log.d(TAG, "Deleting existing file " + badFile.toString());
        badFile.delete();
      }
    }
  }


  private boolean downloadFile(String sourceFilenameBase, File destinationFile)
      throws IOException {
    try {
      return downloadGzippedFileHttp(new URL(CaptureActivity.DOWNLOAD_BASE + sourceFilenameBase +
          ".gz"), 
          destinationFile);
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException("Bad URL string.");
    }
  }

  private boolean downloadGzippedFileHttp(URL url, File destinationFile)
      throws IOException {
    // Send an HTTP GET request for the file
    Log.d(TAG, "Sending GET request to " + url + "...");
    publishProgress("Downloading data for " + languageName + "...", "0");
    HttpURLConnection urlConnection = null;
    urlConnection = (HttpURLConnection) url.openConnection();
    urlConnection.setAllowUserInteraction(false);
    urlConnection.setInstanceFollowRedirects(true);
    urlConnection.setRequestMethod("GET");
    urlConnection.connect();
    if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
      Log.e(TAG, "Did not get HTTP_OK response.");
      Log.e(TAG, "Response code: " + urlConnection.getResponseCode());
      Log.e(TAG, "Response message: " + urlConnection.getResponseMessage().toString());
      return false;
    }
    int fileSize = urlConnection.getContentLength();
    InputStream inputStream = urlConnection.getInputStream();
    File tempFile = new File(destinationFile.toString() + ".gz.download");

    // Stream the file contents to a local file temporarily
    Log.d(TAG, "Streaming download to " + destinationFile.toString() + ".gz.download...");
    final int BUFFER = 8192;
    FileOutputStream fileOutputStream = null;
    Integer percentComplete;
    int percentCompleteLast = 0;
    try {
      fileOutputStream = new FileOutputStream(tempFile);
    } catch (FileNotFoundException e) {
      Log.e(TAG, "Exception received when opening FileOutputStream.", e);
    }
    int downloaded = 0;
    byte[] buffer = new byte[BUFFER];
    int bufferLength = 0;
    while ((bufferLength = inputStream.read(buffer, 0, BUFFER)) > 0) {
      fileOutputStream.write(buffer, 0, bufferLength);
      downloaded += bufferLength;
      percentComplete = (int) ((downloaded / (float) fileSize) * 100);
      if (percentComplete > percentCompleteLast) {
        publishProgress(
            "Downloading data for " + languageName + "...",
            percentComplete.toString());
        percentCompleteLast = percentComplete;
      }
    }
    fileOutputStream.close();
    if (urlConnection != null) {
      urlConnection.disconnect();
    }

    // Uncompress the downloaded temporary file into place, and remove the temporary file
    try {
      Log.d(TAG, "Unzipping...");
      gunzip(tempFile,
          new File(tempFile.toString().replace(".gz.download", "")));
      return true;
    } catch (FileNotFoundException e) {
      Log.e(TAG, "File not available for unzipping.");
    } catch (IOException e) {
      Log.e(TAG, "Problem unzipping file.");
    }
    return false;
  }

  private void gunzip(File zippedFile, File outFilePath)
      throws FileNotFoundException, IOException {
    int uncompressedFileSize = getGzipSizeUncompressed(zippedFile);
    Integer percentComplete;
    int percentCompleteLast = 0;
    int unzippedBytes = 0;
    final Integer progressMin = 0;
    int progressMax = 100 - progressMin;
    publishProgress("Uncompressing data for " + languageName + "...",
        progressMin.toString());

    // If the file is a tar file, just show progress to 50%
    String extension = zippedFile.toString().substring(
        zippedFile.toString().length() - 16);
    if (extension.equals(".tar.gz.download")) {
      progressMax = 50;
    }
    GZIPInputStream gzipInputStream = new GZIPInputStream(
        new BufferedInputStream(new FileInputStream(zippedFile)));
    OutputStream outputStream = new FileOutputStream(outFilePath);
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
        outputStream);

    final int BUFFER = 8192;
    byte[] data = new byte[BUFFER];
    int len;
    while ((len = gzipInputStream.read(data, 0, BUFFER)) > 0) {
      bufferedOutputStream.write(data, 0, len);
      unzippedBytes += len;
      percentComplete = (int) ((unzippedBytes / (float) uncompressedFileSize) * progressMax)
          + progressMin;

      if (percentComplete > percentCompleteLast) {
        publishProgress("Uncompressing data for " + languageName
            + "...", percentComplete.toString());
        percentCompleteLast = percentComplete;
      }
    }
    gzipInputStream.close();
    bufferedOutputStream.flush();
    bufferedOutputStream.close();

    if (zippedFile.exists()) {
      zippedFile.delete();
    }
  }

  private int getGzipSizeUncompressed(File zipFile) throws IOException {
    RandomAccessFile raf = new RandomAccessFile(zipFile, "r");
    raf.seek(raf.length() - 4);
    int b4 = raf.read();
    int b3 = raf.read();
    int b2 = raf.read();
    int b1 = raf.read();
    raf.close();
    return (b1 << 24) | (b2 << 16) + (b3 << 8) + b4;
  }

  private boolean installFromAssets(String sourceFilename, File modelRoot,
                                    File destinationFile) throws IOException {
    String extension = sourceFilename.substring(sourceFilename.lastIndexOf('.'),
        sourceFilename.length());
    try {
      if (extension.equals(".zip")) {
        return installZipFromAssets(sourceFilename, modelRoot, destinationFile);
      } else {
        throw new IllegalArgumentException("Extension " + extension
            + " is unsupported.");
      }
    } catch (FileNotFoundException e) {
      Log.d(TAG, "Language not packaged in application assets.");
    }
    return false;
  }

  private boolean installZipFromAssets(String sourceFilename,
                                       File destinationDir, File destinationFile) throws IOException,
          FileNotFoundException {
    // Attempt to open the zip archive
    publishProgress("Uncompressing data for " + languageName + "...", "0");
    ZipInputStream inputStream = new ZipInputStream(context.getAssets().open(sourceFilename));

    // Loop through all the files and folders in the zip archive (but there should just be one)
    for (ZipEntry entry = inputStream.getNextEntry(); entry != null; entry = inputStream
        .getNextEntry()) {
      destinationFile = new File(destinationDir, entry.getName());

      if (entry.isDirectory()) {
        destinationFile.mkdirs();
      } else {
        // Note getSize() returns -1 when the zipfile does not have the size set
        long zippedFileSize = entry.getSize();

        // Create a file output stream
        FileOutputStream outputStream = new FileOutputStream(destinationFile);
        final int BUFFER = 8192;

        // Buffer the output to the file
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, BUFFER);
        int unzippedSize = 0;

        // Write the contents
        int count = 0;
        Integer percentComplete = 0;
        Integer percentCompleteLast = 0;
        byte[] data = new byte[BUFFER];
        while ((count = inputStream.read(data, 0, BUFFER)) != -1) {
          bufferedOutputStream.write(data, 0, count);
          unzippedSize += count;
          percentComplete = (int) ((unzippedSize / (long) zippedFileSize) * 100);
          if (percentComplete > percentCompleteLast) {
            publishProgress("Uncompressing data for " + languageName + "...", 
                percentComplete.toString(), "0");
            percentCompleteLast = percentComplete;
          }
        }
        bufferedOutputStream.close();
      }
      inputStream.closeEntry();
    }
    inputStream.close();
    return true;
  }

  @Override
  protected void onProgressUpdate(String... message) {
    super.onProgressUpdate(message);
    int percentComplete = 0;

    percentComplete = Integer.parseInt(message[1]);
    dialog.setMessage(message[0]);
    dialog.setProgress(percentComplete);
    dialog.show();
  }

  @Override
  protected void onPostExecute(Boolean result) {
    super.onPostExecute(result);
    
    try {
      indeterminateDialog.dismiss();
    } catch (IllegalArgumentException e) {
      // Catch "View not attached to window manager" error, and continue
    }

    if (result) {
      // Restart recognition
      activity.resumeOCR();
      activity.showLanguageName();
    } else {
      activity.showErrorMessage("Error", "Network is unreachable - cannot download language data. "
          + "Please enable network access and restart this app.");
    }
  }
}