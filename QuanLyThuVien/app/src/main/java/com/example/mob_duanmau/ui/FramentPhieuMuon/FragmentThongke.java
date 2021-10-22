package com.example.mob_duanmau.ui.FramentPhieuMuon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mob_duanmau.Adapter.ThongkeAdapter;
import com.example.mob_duanmau.DAO.ThongKeDAO;
import com.example.mob_duanmau.Model.TOP;
import com.example.mob_duanmau.R;

import java.util.ArrayList;


public class FragmentThongke extends Fragment {
    ListView lv;
    ArrayList<TOP> list;
    ThongkeAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        lv = view.findViewById(R.id.lv_top);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
        list = (ArrayList<TOP>) thongKeDAO.getTop();
        adapter = new ThongkeAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        return view;
    }
}