package com.rock.reliantdispatch.Base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.rock.reliantdispatch.Main.Carrier.DirectDispatchedToMe.DirectDispatchedToMeFragment;
import com.rock.reliantdispatch.Main.Carrier.PostExportFragment;
import com.rock.reliantdispatch.Main.Shipper.VehicleList.VehicleListFragment;
import com.rock.reliantdispatch.Main.Shipper.DirectDispatch.DirectDispatchFragment;
import com.rock.reliantdispatch.Main.Shipper.CarrierTrailer.CarrierTrailerFragment;
import com.rock.reliantdispatch.Main.Shipper.DraftList.DraftListFragment;
import com.rock.reliantdispatch.Main.MainActivity;
import com.rock.reliantdispatch.Main.Resource.CarrierService.CarrierServiceFragment;
import com.rock.reliantdispatch.Main.Resource.ClassifiedSearch.ClassifiedSearchFragment;
import com.rock.reliantdispatch.Main.Carrier.NewSearchFragment;
import com.rock.reliantdispatch.Main.Carrier.SavedRouteSearch.SavedRouteFragment;
import com.rock.reliantdispatch.Main.Carrier.TruckSpace.TruckSpaceFragment;
import com.rock.reliantdispatch.R;

/**
 * Created by michalejackson on 3/20/18.
 */

public abstract class BaseFragment extends Fragment {

    protected MainActivity parent;
    protected BaseFragment ChildFragment;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity)
            parent = (MainActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parent = null;
        ChildFragment = null;
    }

    private FRAGMENTS currentFragment;
    public enum FRAGMENTS {
        VehicleListFragment, DraftListFragment, DirectDispathFragment, CarrierTrailerFragment, ShipperPostExportFragment, NewSearchFragment, SavedRouteFragment, CarrierServiceFragment, ClassifiedSearchFragment, TruckSpaceFragment, CarrierListExport
        , CarrierDispatchedToMe
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        if(childFragment instanceof BaseFragment)
            ChildFragment = (BaseFragment) childFragment;
    }

    public void MoveToSelectedChildFragment(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_vehicle_list:
                addChildFragment(FRAGMENTS.VehicleListFragment);
                break;
            case R.id.menu_drafts:
                addChildFragment(FRAGMENTS.DraftListFragment);
                break;
            case R.id.menu_direct_dispatch:
                addChildFragment(FRAGMENTS.DirectDispathFragment);
                break;
            case R.id.menu_search_carrier:
                addChildFragment(FRAGMENTS.CarrierTrailerFragment);
                break;
            case R.id.menu_post_export:
                addChildFragment(FRAGMENTS.CarrierListExport);
                break;
            //end post
            case R.id.findLoads_export:
                addChildFragment(FRAGMENTS.ShipperPostExportFragment);
                break;
            case R.id.newSearch:
                addChildFragment(FRAGMENTS.NewSearchFragment);
                break;
            case R.id.saveRouteSearch:
                addChildFragment(FRAGMENTS.SavedRouteFragment);
                break;
            case R.id.myListedTruckSpace:
                addChildFragment(FRAGMENTS.TruckSpaceFragment);
                break;
            //end 
            case R.id.carrierServices:
                addChildFragment(FRAGMENTS.CarrierServiceFragment);
                break;
            case R.id.classifiedSearch:
                addChildFragment(FRAGMENTS.ClassifiedSearchFragment);
                break;
            case R.id.dispatchOrdersToMe:
                addChildFragment(FRAGMENTS.CarrierDispatchedToMe);
                break;
            default:
                addChildFragment(FRAGMENTS.DirectDispathFragment);
                break;
        }
    }

    public void setCurrentFragment(FRAGMENTS currentFragment) {
        this.currentFragment = currentFragment;
    }

    public void addChildFragment(FRAGMENTS donationfragment) {
        if (currentFragment != donationfragment) {
            setCurrentFragment(donationfragment);
            BaseFragment f;
            switch (donationfragment) {
                case VehicleListFragment:
                    f = VehicleListFragment.newInstance();
                    break;
                case DraftListFragment:
                    f = DraftListFragment.newInstance();
                    break;
                case DirectDispathFragment:
                    f = DirectDispatchFragment.newInstance();
                    break;
                case CarrierTrailerFragment:
                    f = CarrierTrailerFragment.newInstance();
                    break;
                case ShipperPostExportFragment:
                    f = PostExportFragment.newInstance();
                    break;
                    //post end
                case NewSearchFragment:
                    f = NewSearchFragment.newInstance();
                    break;
                case SavedRouteFragment:
                    f = SavedRouteFragment.newInstance();
                    break;
                case TruckSpaceFragment:
                    f = TruckSpaceFragment.newInstance();
                    break;
                case CarrierServiceFragment:
                    f = CarrierServiceFragment.newInstance();
                    break;
                case ClassifiedSearchFragment:
                    f = ClassifiedSearchFragment.newInstance();
                    break;
                case CarrierListExport:
                    f = PostExportFragment.newInstance();
                    break;
                case CarrierDispatchedToMe:
                    f = DirectDispatchedToMeFragment.newInstance();
                    break;
                default:
                    f = DirectDispatchFragment.newInstance();
                    break;
            }
            attachFragment(f);
        }
    }

    private void attachFragment(BaseFragment f) {
        if (f != null) {
            while (getChildFragmentManager().getBackStackEntryCount() > 0) {
                getChildFragmentManager().popBackStackImmediate();
            }
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.child_content, f).addToBackStack(null)
                    .commit();
        }
    }
}