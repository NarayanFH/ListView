package com.example.textlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import android.widget.ImageView;
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

                obj.notifyItemInserted(list.size() - 1);
                first.setText("");
                second.setText("");
                third.setText("");
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

            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Do you really want to delete?");
                    builder.setPositiveButton("OK", (dialog, which) -> {
                        deleteItem(holder.getAdapterPosition());
                        dialog.dismiss();

                    });
                    builder.setNegativeButton("Cancel", (dialog, which) -> {
//                        holder..setSelection(0);
                        dialog.dismiss();
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            });

            holder.edit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    View dig_view= LayoutInflater.from(MainActivity.this)
                            .inflate(R.layout.edit_dialog, null,false);

                    TextView e1,e2,e3;
                    Button up;

                    e1 = dig_view.findViewById(R.id.e1);
                    e2 = dig_view.findViewById(R.id.e2);
                    e3 = dig_view.findViewById(R.id.e3);
                    up =dig_view.findViewById(R.id.update_button);

                    dialog.setView(dig_view);

                  int pos=  holder.getAdapterPosition();

                    e1.setText(list.get(pos).getA());
                    e2.setText(list.get(pos).getB());
                    e3.setText(list.get(pos).getC());




                    dialog.show();

                    up.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            FileClass obj = new FileClass();
                            RView ob = new RView();

                            ob.setA(e1.getText().toString());
                            ob.setB(e2.getText().toString());
                            ob.setC(e3.getText().toString());

                            list.get(pos).setA(e1.toString());
                            list.get(pos).setB(e2.toString());
                            list.get(pos).setC(e3.toString());

                            notifyItemChanged(pos);
                        }
                    });
                }

            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
        private void deleteItem(int position) {
            list.remove(position);
            notifyItemRemoved(position);
//            notifyItemRangeChanged(position, list.size());
//
        }




        private class NewHolder extends RecyclerView.ViewHolder{
            TextView t1,t2,t3;
            ImageView del;
            ImageView edit;
            Button up;


            public NewHolder(@NonNull View itemView) {
                super(itemView);
                t1= itemView.findViewById(R.id.t1);
                t2= itemView.findViewById(R.id.t2);
                t3= itemView.findViewById(R.id.t3);
                del = itemView.findViewById(R.id.del);
                edit =itemView.findViewById(R.id.edit);
                up =findViewById(R.id.update_button);
            }
        }
    }


}