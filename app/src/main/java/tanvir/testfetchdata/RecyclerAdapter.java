package tanvir.testfetchdata;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USER on 01-Feb-17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {


    private ArrayList<PersonDetails> personDetailsArrayList = new ArrayList<PersonDetails>();

    Activity activity;


    public RecyclerAdapter(Activity activity, ArrayList<PersonDetails> personDetailsArrayList) {
        this.personDetailsArrayList = personDetailsArrayList;
        this.activity = activity;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv, parent, false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }


    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {


        PersonDetails personDetails = personDetailsArrayList.get(position);


        holder.nameTV.setText(personDetails.getName());
        holder.emailTV.setText(personDetails.getEmail());
        holder.mobile_numTV.setText(personDetails.getMobile_num());

    }

    @Override
    public int getItemCount() {
        return personDetailsArrayList.size();
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;
        TextView mobile_numTV;
        TextView emailTV;
        ImageView imageView;

        public RecyclerViewHolder(View view) {
            super(view);

            nameTV = view.findViewById(R.id.nameTV);
            mobile_numTV = view.findViewById(R.id.mobile_numTV);
            emailTV = view.findViewById(R.id.emailTV);


        }
    }


}
