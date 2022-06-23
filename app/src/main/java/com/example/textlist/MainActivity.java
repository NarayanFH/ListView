package com.example.textlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText first,second,third;
    Button bt;
    List<RView> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);

        bt= findViewById(R.id.button);
        RecyclerView recyclerView = findViewById(R.id.list);

        FileClass obj = new FileClass();

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(obj);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RView ob = new RView();

                ob.setA(first.getText().toString());
                ob.setB(second.getText().toString());
                ob.setC(third.getText().toString());

                list.add(ob);
//                first.setText("");
//                second.setText("");
//                third.setText("");
            }
        });

    }

    private class FileClass extends RecyclerView.Adapter<FileClass.NewHolder> {

        @NonNull
        @Override
        public NewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_view, parent, false);
            return new NewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewHolder holder, int position) {

            holder.t1.setText(list.get(position).getA());
            holder.t2.setText(list.get(position).getB());
            holder.t3.setText(list.get(position).getC());

        }

        @Override
        public int getItemCount() {


            return list.size();
        }

        private class NewHolder extends RecyclerView.ViewHolder{
            TextView t1,t2,t3;

            public NewHolder(@NonNull View itemView) {
                super(itemView);
                t1= itemView.findViewById(R.id.t1);
                t2= itemView.findViewById(R.id.t2);
                t3= itemView.findViewById(R.id.t3);


            }
        }
    }


}