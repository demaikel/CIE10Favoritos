package demaikel.cie10favoritos.views.main.cie10List;


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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import demaikel.cie10favoritos.R;

public class FavoriteFragment extends Fragment {

    private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance() {

        return new FavoriteFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("favorites").child(uid);
        RecyclerView recycler = (RecyclerView) view;
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<String, Cie10Holder>(String.class, R.layout.list_item_cie,Cie10Holder.class, reference) {
            @Override
            protected void populateViewHolder(Cie10Holder viewHolder, String model, int position) {

                viewHolder.setName(model,uid);
            }
        };

        /*reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getKey();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        recycler.setAdapter(adapter);
    }

    public static class Cie10Holder extends RecyclerView.ViewHolder {
        private final TextView mNameField;
        private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("favorites");


        public Cie10Holder(View itemView) {
            super(itemView);
            mNameField = (TextView) itemView.findViewById(R.id.receiverTv);
        }

        public void setName(final String name, final String uid) {
            mNameField.setText(name);
            mNameField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reference.child(uid).child(name).removeValue();
                }
            });
        }
    }

}
