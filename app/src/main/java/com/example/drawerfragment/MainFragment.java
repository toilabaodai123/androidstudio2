package com.example.drawerfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Phim> phimList;
    private RequestQueue request;
    private TextView test3,test4;
    public String b,noidungphim;



    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView=(RecyclerView)v.findViewById(R.id.recyclerviewphim);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(),RecyclerView.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        phimList=new ArrayList<>();
        request= Volley.newRequestQueue(v.getContext());
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        test3=(TextView)headerView.findViewById(R.id.tvtaikhoan);
        String a =test3.getText().toString();
        Toast.makeText(getContext(),a+"bbbbbb", Toast.LENGTH_SHORT).show();
        loadJSONPhim();

/*
        NavigationView navigationView;
        navigationView=v.findViewById(R.id.navigation_view);
        View hoten = navigationView.getHeaderView(0);
        test3=(TextView) hoten.findViewById(R.id.tvhoten);
*/

        return v;




    }

    private void loadJSONPhim() {
        //Toast.makeText(recyclerView.getContext(),"a", Toast.LENGTH_SHORT).show();
        String url="http://192.168.1.103/apiloadphim2.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(recyclerView.getContext(),response.toString(), Toast.LENGTH_SHORT).show();
                for(int i = 0 ; i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String id=object.getString("id");
                        String tenphim=object.getString("tenphim");
                        noidungphim =object.getString("noidungphim");
                        String tenloaiphim = object.getString("tenloaiphim");
                        phimList.add(new Phim(id,tenphim,noidungphim,tenloaiphim));
                        String a =test3.getText().toString();
                        adapter = new PhimAdapter(a,phimList,recyclerView.getContext(),noidungphim);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        Toast.makeText(recyclerView.getContext(),e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(recyclerView.getContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jsonArrayRequest);
    }


}
