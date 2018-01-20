package com.example.nhem.eventwithlove.event.activities.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhem.eventwithlove.R;
import com.example.nhem.eventwithlove.event.activities.Preferences;
import com.example.nhem.eventwithlove.event.activities.models.requests.QuestionRequest;
import com.example.nhem.eventwithlove.event.activities.models.responses.QuestionResponse;
import com.example.nhem.eventwithlove.event.activities.network.RetrofitFactory;
import com.example.nhem.eventwithlove.event.activities.network.api_routes.QuestionRoute;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    EditText etQuestion;
    Button btSend;

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        setupUI(view);
        return view;
    }

    private void setupUI(View view) {
        etQuestion = view.findViewById(R.id.etQuestion);
        btSend = view.findViewById(R.id.btSend);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionRoute questionRoute = RetrofitFactory.getInstance().create(QuestionRoute.class);
                questionRoute.sendQuestion(new QuestionRequest(Preferences.getInstance().getEvent(), etQuestion.getText().toString()))
                        .enqueue(new Callback<QuestionResponse>() {
                            @Override
                            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                                Log.d("", "onResponse: ");
                                Toast.makeText(getActivity(), "Gửi thành công", Toast.LENGTH_SHORT).show();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        etQuestion.setText("");
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                                Log.d("", "onFailure: " + t.toString());
                            }
                        });
            }
        });
    }

}
