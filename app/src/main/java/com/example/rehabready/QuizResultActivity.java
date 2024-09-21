package com.example.rehabready;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.Intrinsics;

public final class QuizResultActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_quiz_result);
        View var10000 = this.findViewById(R.id.btn_finish);
        Intrinsics.checkNotNullExpressionValue(var10000, "findViewById(R.id.btn_finish)");
        Button btnFinish = (Button)var10000;
        btnFinish.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                QuizResultActivity.this.startActivity(new Intent((Context) QuizResultActivity.this, MainActivity.class));
                QuizResultActivity.this.finish();
            }
        }));
    }
}
