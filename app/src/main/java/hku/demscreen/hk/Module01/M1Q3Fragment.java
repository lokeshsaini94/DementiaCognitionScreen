package hku.demscreen.hk.Module01;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import hku.demscreen.hk.GlobalVariables;
import hku.demscreen.hk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class M1Q3Fragment extends Fragment {

    ImageView sound;
    ImageView timer;
    ImageView scoreCorrect;
    ImageView scoreWrong;

    ImageView previousQuestion;
    ImageView nextQuestion;

    public M1Q3Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_m1q3, container, false);

        sound = (ImageView) root.findViewById(R.id.sound);
        timer = (ImageView) root.findViewById(R.id.timer);
        scoreCorrect = (ImageView) root.findViewById(R.id.score_correct);
        scoreWrong = (ImageView) root.findViewById(R.id.score_wrong);

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Sound will Play", Toast.LENGTH_SHORT).show();
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Timer will Start/Stop", Toast.LENGTH_SHORT).show();
            }
        });

        scoreCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                GlobalVariables.m1Score[2] = 1;
                GlobalVariables.m1NextPage();
            }
        });

        scoreWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                GlobalVariables.m1Score[2] = 0;
                GlobalVariables.m1NextPage();
            }
        });

        previousQuestion = (ImageView) root.findViewById(R.id.previous_question_3);
        nextQuestion = (ImageView) root.findViewById(R.id.next_question_3);

        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariables.m1PreviousPage();
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariables.m1NextPage();
            }
        });

        return root;
    }
}
