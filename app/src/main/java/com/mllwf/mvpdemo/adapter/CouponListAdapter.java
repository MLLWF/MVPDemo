package com.mllwf.mvpdemo.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mllwf.model.CouponBo;
import com.mllwf.mvpdemo.R;
import com.mllwf.mvpdemo.util.CouponPriceUtil;

/**
 * Created by ML on 2017/4/19.
 */

public class CouponListAdapter extends KBaseAdapter<CouponBo> {


    public CouponListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_list_coupon, viewGroup, false);
            holder = new ViewHolder();
            holder.titleText = (TextView) view.findViewById(R.id.text_item_title);
            holder.infoText = (TextView) view.findViewById(R.id.text_item_info);
            holder.priceText = (TextView) view.findViewById(R.id.text_item_price);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (isEmpty()) {
            return view;
        }
        CouponBo coupon = mList.get(position);
        holder.titleText.setText(coupon.getName());
        holder.infoText.setText(coupon.getIntroduce());
        SpannableString priceString;
        // 根据不同的券类型展示不同的价格显示方式
        switch (coupon.getModelType()) {
            default:
            case CouponBo.TYPE_CASH:
                priceString = CouponPriceUtil.getCashPrice(context, coupon.getFaceValue(), coupon.getEstimateAmount());
                break;
            case CouponBo.TYPE_DEBIT:
                priceString = CouponPriceUtil.getVoucherPrice(context, coupon.getDebitAmount(), coupon.getMiniAmount());
                break;
            case CouponBo.TYPE_DISCOUNT:
                priceString = CouponPriceUtil.getDiscountPrice(context, coupon.getDiscount(), coupon.getMiniAmount());
                break;
        }
        holder.priceText.setText(priceString);

        return view;
    }

    static class ViewHolder {
        TextView titleText;
        TextView infoText;
        TextView priceText;
    }
}
