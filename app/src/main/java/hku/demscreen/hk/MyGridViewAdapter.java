package hku.demscreen.hk;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class MyGridViewAdapter extends ArrayAdapter<String> {

    MyGridViewAdapter(Context context, String[] names) {
        super(context, R.layout.cards_layout, names);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View view = myInflater.inflate(R.layout.cards_layout, parent, false);

        String singleName = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.card_content);
        ImageView selectedTick = (ImageView) view.findViewById(R.id.card_tick);
        CardView moduleCardView = (CardView) view.findViewById(R.id.card_container);

        textView.setText(singleName);

        if (GlobalVariables.modulesSelected[position]) {
            selectedTick.setVisibility(View.VISIBLE);
            moduleCardView.setCardBackgroundColor(Color.WHITE);

        } else {
            selectedTick.setVisibility(View.GONE);
            moduleCardView.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        }

        return view;
    }
}
