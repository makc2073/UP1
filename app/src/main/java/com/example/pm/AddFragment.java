package com.example.pm;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.room.Room;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

public class AddFragment extends Fragment {
    BootstrapButton addEmpBtn, editEmpBtn, delEmpBtn;
    BootstrapEditText id, name, position, salary, date;
    Switch fried;
    RecyclerView eList;
    boolean friedChek;

    public AddFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);

    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppDatabase db =  Room.databaseBuilder(getContext().getApplicationContext(),
                AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
        final EmployeeDao employeeDao = db.employeeDao();

        addEmpBtn = view.findViewById(R.id.addEmpButton);
        editEmpBtn = view.findViewById(R.id.editButton);
        delEmpBtn = view.findViewById(R.id.delButton);
        id =  view.findViewById(R.id.idEmp);
        name =  view.findViewById(R.id.nameEmp);
        position =  view.findViewById(R.id.positionEmp);
        salary =  view.findViewById(R.id.salarylEmp);
        date =  view.findViewById(R.id.recapeDateEmp);
        fried = view.findViewById(R.id.switchFried);
        eList = (RecyclerView) view.findViewById(R.id.employeeList);
        addEmpBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try{
                if(fried.isChecked()){
                    friedChek = true;
                }
                else {
                    friedChek = false;
                }
                Employee employee = new Employee();
                employee.id = Long.parseLong(id.getText().toString());
                employee.name = name.getText().toString();
                employee.position = position.getText().toString();
                employee.salary = Float.parseFloat(salary.getText().toString());
                employee.isFired = friedChek;
                employee.receiptDate= date.getText().toString();
                employeeDao.insert(employee);
            }
                catch (Exception e){
                Toast.makeText(getActivity(), "Введите верные данные", Toast.LENGTH_LONG).show();
            }
            }
        });
        editEmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (fried.isChecked()) {
                        friedChek = true;
                    } else {
                        friedChek = false;
                    }
                    Employee employee = new Employee();
                    employee.id = Long.parseLong(id.getText().toString());
                    employee.name = name.getText().toString();
                    employee.position = position.getText().toString();
                    employee.salary = Float.parseFloat(salary.getText().toString());
                    employee.isFired = friedChek;
                    employee.receiptDate = date.getText().toString();
                    employeeDao.update(employee);
                }
                     catch (Exception e){
                    Toast.makeText(getActivity(), "Введите верные данные", Toast.LENGTH_LONG).show();
                }

            }
        });
        delEmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee employee = new Employee();
                employee.id = Long.parseLong(id.getText().toString());
                employeeDao.delete(employee);

            }
        });

    }

}