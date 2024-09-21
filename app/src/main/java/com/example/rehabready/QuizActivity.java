package com.example.rehabready;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

public final class QuizActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        View var10000 = this.findViewById(R.id.btn_psychological_quiz);
        Intrinsics.checkNotNullExpressionValue(var10000, "findViewById(R.id.btn_psychological_quiz)");
        Button btnPsychologicalQuiz = (Button)var10000;
        var10000 = this.findViewById(R.id.btn_self_help_quiz);
        Intrinsics.checkNotNullExpressionValue(var10000, "findViewById(R.id.btn_self_help_quiz)");
        Button btnSelfHelpQuiz = (Button)var10000;
        var10000 = this.findViewById(R.id.btn_feelings_journal);
        Intrinsics.checkNotNullExpressionValue(var10000, "findViewById(R.id.btn_feelings_journal)");
        Button btnFeelingsJournal = (Button)var10000;
        btnFeelingsJournal.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                QuizActivity.this.startActivity(new Intent((Context) QuizActivity.this, JournalEntryActivity.class));
            }
        }));
        btnPsychologicalQuiz.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                QuizActivity.this.startQuiz(1);
            }
        }));
        btnSelfHelpQuiz.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                QuizActivity.this.startQuiz(2);
            }
        }));
        btnFeelingsJournal.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                QuizActivity.this.startQuiz(1);
            }
        }));
    }

    private final void startQuiz(int quizType) {
        Intent intent = new Intent((Context)this, QuizQuestionActivity.class);
        intent.putExtra("quiz_type", quizType);
        this.startActivity(intent);
        this.finish();
    }
}
