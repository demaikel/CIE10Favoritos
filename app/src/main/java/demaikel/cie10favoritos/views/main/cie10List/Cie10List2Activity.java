package demaikel.cie10favoritos.views.main.cie10List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import demaikel.cie10favoritos.R;

public class Cie10List2Activity extends AppCompatActivity {

    public static Cie10List2Activity newInstance() {
        return new Cie10List2Activity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_cie10_2);

        String name = getIntent().getStringExtra("name");

        RecyclerView recycler = (RecyclerView) findViewById(R.id.list_item_cie102);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("cie10").child(name);

        FirebaseRecyclerAdapter mAdapter = new FirebaseRecyclerAdapter<String, Cie10Holder>(String.class, R.layout.list_item_cie, Cie10Holder.class, reference) {
            @Override
            protected void populateViewHolder(Cie10Holder viewHolder, String model, int position) {
                viewHolder.setName(model);
            }
        };
        recycler.setAdapter(mAdapter);

    }


    public static class Cie10Holder extends RecyclerView.ViewHolder {
        View mView;
        TextView field;
        private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("favorites").child(uid);

        public Cie10Holder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(final String name) {
            field = (TextView) mView.findViewById(R.id.receiverTv);
            field.setText(name);
            field.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reference.child(name).setValue(name); }
            });
        }

    }
}
