package fyales.listviewloadmore.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author fyales
 * @date 12/11/14.
 */
public class CountryAdapter extends BaseAdapter{
    private List<String> countries;
    private LayoutInflater inflater;

    public CountryAdapter(Context context ,List<String> list) {

        this.countries = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(android.R.layout.simple_list_item_1,null);
        TextView country = (TextView)convertView.findViewById(android.R.id.text1);
        country.setText(countries.get(position));
        return convertView;
    }
}
