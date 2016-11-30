package demaikel.cie10favoritos.views.main.cie10List;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import demaikel.cie10favoritos.R;


public class Cie10ListFragment extends Fragment {

    public Cie10ListFragment() {
        // Required empty public constructor
    }

    public static Cie10ListFragment newInstance() {
        return new Cie10ListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cie10_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("cie10-names");
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<String, Cie10Holder>(String.class, R.layout.list_item_cie, Cie10Holder.class, reference) {
            @Override
            protected void populateViewHolder(Cie10Holder viewHolder, String model, int position) {
                viewHolder.setName(model);


                final String name = model;
                viewHolder.mNameField.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getActivity(), Cie10List2Activity.class);
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }


    public static class Cie10Holder extends RecyclerView.ViewHolder {
        private final TextView mNameField;


        public Cie10Holder(View itemView) {
            super(itemView);
            mNameField = (TextView) itemView.findViewById(R.id.receiverTv);
        }

        public void setName(final String name) {
            mNameField.setText(name);
        }
    }
}

