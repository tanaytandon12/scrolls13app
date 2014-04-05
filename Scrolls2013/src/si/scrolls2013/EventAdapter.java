package si.scrolls2013;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class EventAdapter extends ArrayAdapter<Event> {

	Context context;
	int layoutResourceId;    
	Event data[]=null; 
	
	public EventAdapter(Context context, int textViewResourceId, Event[] objects) {
		super(context, textViewResourceId, objects);
		this.context=context;
		this.layoutResourceId = textViewResourceId;
		this.data = objects;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EventHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new EventHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            
            row.setTag(holder);
        }
        else
        {
            holder = (EventHolder)row.getTag();
        }
        
        Event event = data[position];
        holder.imgIcon.setImageResource(event.icon);
        
        return row;
    }
    
	
	static class EventHolder
	{
		ImageView imgIcon;
	}

}
