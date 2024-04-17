package com.example.resturentsilverspoons.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.api.TableApi;
import com.example.resturentsilverspoons.model.TableModel;

import java.util.Calendar;


public class TableFragment extends Fragment {


    Button btn_date,btn_time,btn_table_boook;

    RadioGroup rdg_numguest ;
    ImageView img_table5,img_table4,img_table3,img_table2,img_table1;

    RadioButton radio1,radio2,radio3,radio4,radio5;
    TextView tvshow_date,tvshow_time,showguest,tvtnumber;

    View view;

    String timeFormat,time;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_table, container, false);
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_date = view.findViewById(R.id.btn_date);
        tvshow_date = view.findViewById(R.id.tvshow_date);
        btn_time=view.findViewById(R.id.btn_time);
        tvshow_time=view.findViewById(R.id.tvshow_time);
        btn_table_boook=view.findViewById(R.id.btn_table_boook);
        showguest=view.findViewById(R.id.showguest);
        img_table5=view.findViewById(R.id.img_table5);
        img_table4=view.findViewById(R.id.img_table4);
        img_table3=view.findViewById(R.id.img_table3);
        img_table2=view.findViewById(R.id.img_table2);
        img_table1=view.findViewById(R.id.img_table1);
        rdg_numguest=view.findViewById(R.id.rdg_numguest);
        tvtnumber=view.findViewById(R.id.tvtnumber);

        radio1=view.findViewById(R.id.radio1);
        radio2=view.findViewById(R.id.radio2);
        radio3=view.findViewById(R.id.radio3);
        radio4=view.findViewById(R.id.radio4);
        radio5=view.findViewById(R.id.radio5);


        rdg_numguest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                // Hide all ImageView elements first
                img_table1.setVisibility(View.GONE);
                img_table2.setVisibility(View.GONE);
                img_table3.setVisibility(View.GONE);
                img_table4.setVisibility(View.GONE);
                img_table5.setVisibility(View.GONE);

                // Then show the corresponding ImageView based on the selected radio button
                if (radio1.isChecked()) {
                    img_table1.setVisibility(View.VISIBLE);
                } else if (radio2.isChecked()) {
                    img_table2.setVisibility(View.VISIBLE);
                } else if (radio3.isChecked()) {
                    img_table3.setVisibility(View.VISIBLE);
                } else if (radio4.isChecked()) {
                    img_table4.setVisibility(View.VISIBLE);
                } else if (radio5.isChecked()) {
                    img_table5.setVisibility(View.VISIBLE);
                }
            }
        });




        btn_table_boook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date=tvshow_date.getText().toString().trim();
                String time=tvshow_time.getText().toString().trim();


                String guest="0";

                if (radio1.isChecked()){

                    guest="1";
                } else if (radio2.isChecked()) {
                    guest="2";
                } else if (radio3.isChecked()) {

                    guest="3";
                } else if (radio4.isChecked()) {
                    guest="4";
                } else if (radio5.isChecked()) {
                    guest="5+";
                } else {
                    showguest.getText().toString().trim();
                }

                String tnumber="0";

                if (radio1.isChecked()){
                    tnumber="1";
                } else if (radio2.isChecked()) {
                    tnumber="2";
                } else if (radio3.isChecked()) {
                    tnumber="3";
                } else if (radio4.isChecked()) {
                    tnumber="4";
                } else if (radio5.isChecked()) {
                    tnumber="5";
                } else {
                    tvtnumber.getText().toString().trim();
                }


                if (date.length()==0){
                    Toast.makeText(getActivity(), "Enter valid date", Toast.LENGTH_SHORT).show();
                } else if (time.length()==0) {
                    Toast.makeText(getActivity(), "Enter valid time", Toast.LENGTH_SHORT).show();

                }
                else {
                    TableModel model=new TableModel("","",tnumber,guest,date,time,"");
                    new TableApi().addtable(getActivity(),model);
                }


            }
        });



        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                open_date_dialog();


            }
        });


        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int selectedYear = 2024; // Example value
//                int selectedMonth = 3;   // Example value
//                int selectedDay = 27;    // Example value
//
//                // Call open_time_dialog() with the selected date
//                open_time_dialog(selectedYear, selectedMonth, selectedDay);


                showTimePickerDialog();
            }
        });

    }

    public void open_date_dialog() {
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // Get the current date
                Calendar calendar = Calendar.getInstance();
                int currentYear = calendar.get(Calendar.YEAR);
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

                // Adjusting month + 1 since months are zero-indexed (0-11)
                String selectedDate = String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);

                // Check if the selected date is yesterday
                if (year < currentYear || (year == currentYear && month < currentMonth) || (year == currentYear && month == currentMonth && day < currentDay  - 1)) {
                    // Selected date is yesterday or earlier, show error message
                    Toast.makeText(getContext(), "Please select Valid Date", Toast.LENGTH_SHORT).show();
                    tvshow_date.setText("");
                } else {
                    // Display the selected date in the TextView
                    tvshow_date.setText(selectedDate);
                }
            }
        }, 2024, 2, 5);

        // Set maximum date to today's date
        dialog.getDatePicker().setMinDate(System.currentTimeMillis());

        dialog.show();
    }



    public void open_time_dialog(final int selectedYear, final int selectedMonth, final int selectedDay) {
        // Get the current date
        Calendar currentDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH);
        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        int currentHour = currentDate.get(Calendar.HOUR_OF_DAY);
        int currentMinute = currentDate.get(Calendar.MINUTE);

        // Check if selected date is today or in the future
        if (selectedYear > currentYear || (selectedYear == currentYear && selectedMonth > currentMonth) ||
                (selectedYear == currentYear && selectedMonth == currentMonth && selectedDay >= currentDay)) {
            // If selected date is today or in the future, allow any time selection
            TimePickerDialog dialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hours, int minute) {
                    String ampm = (hours >= 12) ? "PM" : "AM";
                    tvshow_time.setText(String.format("%02d.%02d%s", hours, minute, ampm));
                }
            }, currentHour, currentMinute, false);
            dialog.show();
        } else {
            // If selected date is in the past, restrict time selection to current time or future times
            TimePickerDialog dialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hours, int minute) {
                    // Check if selected time is current time or in the future
                    if (isFutureTime(currentHour, currentMinute, hours, minute)) {
                        String ampm = (hours >= 12) ? "PM" : "AM";
                        tvshow_time.setText(String.format("%02d.%02d%s", hours, minute, ampm));
                    } else {
                        // Show error message if selected time is past
                        Toast.makeText(getContext(), "Please select valid time", Toast.LENGTH_SHORT).show();
                    }
                }
            }, currentHour, currentMinute, false);
            dialog.show();
        }
    }

    private boolean isFutureTime(int currentHour, int currentMinute, int selectedHour, int selectedMinute) {
        if (selectedHour > currentHour) {
            return true;
        } else if (selectedHour == currentHour && selectedMinute >= currentMinute) {
            return true;
        }
        return false;
    }


    public void showTimePickerDialog() {

        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                if (selectedHour >= 16 && selectedHour <= 23) { // Check if selected time is between 4 PM and 11 PM
                    if (selectedHour < 12) {
                        timeFormat = "AM";
                    } else {
                        timeFormat = "PM";
                    }

                    if (timeFormat.equals("PM")) {
                        if (selectedHour == 12) {
                            time = "12";
                        } else {
                            time = String.valueOf(selectedHour - 12);
                        }
                    } else {
                        time = String.valueOf(selectedHour);
                    }

                    tvshow_time.setText(String.format("%s %s", time, timeFormat));
                } else {
                    Toast.makeText(getContext(), "Please select time between 4 PM to 11 PM", Toast.LENGTH_SHORT).show();
                }
            }
        }, hour, 0, false);

        // Set initial time picker dialog to 4 PM
        timePickerDialog.updateTime(16, 0);

        timePickerDialog.show();
    }




}