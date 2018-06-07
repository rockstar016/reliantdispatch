package com.rock.reliantdispatch.Constants;

import com.rock.reliantdispatch.R;

public class OrderConfig {
    public static final int[] ORDER_TYPE_COLOR_ARRAY = {R.color.newState, R.color.pickupState, R.color.deliveredState,
            R.color.card_yellow, R.color.card_green, R.color.graph_background,
            R.color.bar_color, R.color.check_bk_color};

    public static final int[] ORDER_TYPE_STRING_ARRAY = {R.string.offers_counter_offers, R.string.vehicle_list_kind_dispatched,
            R.string.vehicle_list_kind_accepted, R.string.vehicle_list_kind_in_transit,
            R.string.vehicle_list_kind_delivered, R.string.vehicle_list_kind_completed,
            R.string.vehicle_list_kind_canceled, R.string.vehicle_list_kind_archieved};
    public static final int ORDER_TYPE_OFFER = 0;
    public static final int ORDER_TYPE_DISPATCHED = 1;
    public static final int ORDER_TYPE_ACCEPTED = 2;
    public static final int ORDER_TYPE_INTRANSIT = 3;
    public static final int ORDER_TYPE_DELIVERED = 4;
    public static final int ORDER_TYPE_COMPLETED = 5;
    public static final int ORDER_TYPE_CANCELLED = 6;
    public static final int ORDER_TYPE_ARCHIVED = 7;
}
