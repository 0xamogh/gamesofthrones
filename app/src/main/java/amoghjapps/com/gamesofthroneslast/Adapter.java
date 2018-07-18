package amoghjapps.com.gamesofthroneslast;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class Adapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<StringData> attributes;

    public Adapter(Activity context, ArrayList<StringData> stringarray){
        super(context,R.layout.detail_layout,stringarray);
        this.context = context;
        this.attributes = stringarray;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.detail_layout,null , true);
        TextView h = rowView.findViewById(R.id.attribute);
        TextView s = rowView.findViewById(R.id.attributevalue);
        h.setText(attributes.get(position).head);
        s.setText(attributes.get(position).sub);
        return rowView;

    }
}

class StringData
{
    String head, sub;
}