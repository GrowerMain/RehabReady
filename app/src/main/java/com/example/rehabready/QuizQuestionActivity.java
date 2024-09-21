package com.example.rehabready;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class QuizQuestionActivity extends AppCompatActivity implements View.OnClickListener {
    private int mCurrentPosition = 1;
    private ArrayList mQuestionList;
    private int mSelectedOptionPosition;
    private boolean isSelectedAnswer;
    private String mUserName;
    private ProgressBar progressBar;
    private TextView tvProgress;
    private TextView tvQuestion;
    private ImageView ivImage;
    private TextView tvOptionOne;
    private TextView tvOptionTwo;
    private TextView tvOptionThree;
    private TextView tvOptionFour;
    private Button btnSubmit;
    private final int quizType;

    public final int getQuizType() {
        return this.quizType;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_quiz_question);
        int quizType = this.getIntent().getIntExtra("quiz_type", 1);
        Log.d("QuizQuestionActivity", "Selected Quiz Type: " + quizType);
        this.setPassingRetriveDataIntent();
        this.setUpView();
        this.getQuestions();
        this.setQuestionList();
        this.defaultOptionsView();
    }

    public final void setUpView() {
        this.progressBar = (ProgressBar)this.findViewById(R.id.progressBar);
        this.tvProgress = (TextView)this.findViewById(R.id.tv_progress);
        this.tvQuestion = (TextView)this.findViewById(R.id.tv_question);
        this.tvOptionOne = (TextView)this.findViewById(R.id.tv_option_one);
        this.tvOptionTwo = (TextView)this.findViewById(R.id.tv_option_two);
        this.tvOptionThree = (TextView)this.findViewById(R.id.tv_option_three);
        this.tvOptionFour = (TextView)this.findViewById(R.id.tv_option_four);
        this.btnSubmit = (Button)this.findViewById(R.id.btn_submit);
        TextView var10000 = this.tvOptionOne;
        if (var10000 != null) {
            var10000.setOnClickListener((View.OnClickListener)this);
        }

        var10000 = this.tvOptionTwo;
        if (var10000 != null) {
            var10000.setOnClickListener((View.OnClickListener)this);
        }

        var10000 = this.tvOptionThree;
        if (var10000 != null) {
            var10000.setOnClickListener((View.OnClickListener)this);
        }

        var10000 = this.tvOptionFour;
        if (var10000 != null) {
            var10000.setOnClickListener((View.OnClickListener)this);
        }

        Button var1 = this.btnSubmit;
        if (var1 != null) {
            var1.setOnClickListener((View.OnClickListener)this);
        }

    }

    public final void setPassingRetriveDataIntent() {
        this.mUserName = this.getIntent().getStringExtra("user_name");
    }

    public final void getQuestions() {
        int selectedQuizType = this.getIntent().getIntExtra("quiz_type", 1);
        this.mQuestionList = Constants.INSTANCE.getQuestions(selectedQuizType);
    }

    public final void setQuestionList() {
        this.defaultOptionsView();
        ArrayList var10000 = this.mQuestionList;
        if (var10000 != null) {
            ArrayList var1 = var10000;
            boolean var3 = false;
            int currentPosition = this.mCurrentPosition;
            Collection var6 = (Collection)var1;
            if (!var6.isEmpty()) {
                int var8 = var1.size();
                if (1 <= currentPosition) {
                    if (var8 >= currentPosition) {
                        Object var9 = var1.get(currentPosition - 1);
                        Intrinsics.checkNotNullExpressionValue(var9, "questionsList[currentPosition - 1]");
                        QuizQuestion question = (QuizQuestion)var9;
                        ProgressBar var10 = this.progressBar;
                        if (var10 != null) {
                            var10.setProgress(currentPosition);
                        }

                        TextView var11 = this.tvProgress;
                        if (var11 != null) {
                            var11.setText((CharSequence)("" + currentPosition + '/' + var1.size()));
                        }

                        var11 = this.tvQuestion;
                        if (var11 != null) {
                            var11.setText((CharSequence)question.getQuestion());
                        }

                        var11 = this.tvOptionOne;
                        if (var11 != null) {
                            var11.setText((CharSequence)question.getOptionOne());
                        }

                        var11 = this.tvOptionTwo;
                        if (var11 != null) {
                            var11.setText((CharSequence)question.getOptionTwo());
                        }

                        var11 = this.tvOptionThree;
                        if (var11 != null) {
                            var11.setText((CharSequence)question.getOptionThree());
                        }

                        var11 = this.tvOptionFour;
                        if (var11 != null) {
                            var11.setText((CharSequence)question.getOptionFour());
                        }

                        Button var12;
                        if (currentPosition > var1.size()) {
                            var12 = this.btnSubmit;
                            if (var12 != null) {
                                var12.setText((CharSequence)"FINISH");
                            }

                            return;
                        } else {
                            var12 = this.btnSubmit;
                            if (var12 != null) {
                                var12.setText((CharSequence)"SUBMIT");
                            }

                            return;
                        }
                    }
                }
            }

            Toast.makeText((Context)this, (CharSequence)"Invalid question list or position", Toast.LENGTH_SHORT).show();
            this.finish();
        }

    }

    private final void defaultOptionsView() {
        ArrayList options = new ArrayList();
        TextView var10000 = this.tvOptionOne;
        TextView option;
        boolean var4;
        if (var10000 != null) {
            option = var10000;
            var4 = false;
            options.add(0, option);
        }

        var10000 = this.tvOptionTwo;
        if (var10000 != null) {
            option = var10000;
            var4 = false;
            options.add(1, option);
        }

        var10000 = this.tvOptionThree;
        if (var10000 != null) {
            option = var10000;
            var4 = false;
            options.add(2, option);
        }

        var10000 = this.tvOptionFour;
        if (var10000 != null) {
            option = var10000;
            var4 = false;
            options.add(3, option);
        }

        Iterator var3 = options.iterator();

        while(var3.hasNext()) {
            option = (TextView)var3.next();
            option.setTextColor(Color.parseColor("#7A8089"));
            Intrinsics.checkNotNullExpressionValue(option, "option");
            option.setTypeface(Typeface.DEFAULT);
            option.setBackground(ContextCompat.getDrawable((Context)this, R.drawable.default_option_border_bg));
        }

    }

    private final void selectedOptionView(TextView tv, int selectedOptionNum) {
        this.defaultOptionsView();
        this.mSelectedOptionPosition = selectedOptionNum;
        this.isSelectedAnswer = true;
        tv.setTextColor(Color.parseColor("#363A43"));
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setBackground(ContextCompat.getDrawable((Context)this, R.drawable.selected_option_border_bg));
    }

    public void onClick(@Nullable View view) {
        Button var10000 = this.btnSubmit;
        boolean isAnswerNotSubmitDone = Intrinsics.areEqual(var10000 != null ? var10000.getText() : null, "SUBMIT");
        Integer var3 = view != null ? view.getId() : null;
        int var4 = 2131231199;
        boolean var6;
        TextView var11;
        TextView var16;
        if (var3 != null) {
            if (var3 == var4) {
                if (isAnswerNotSubmitDone) {
                    var16 = this.tvOptionOne;
                    if (var16 != null) {
                        var11 = var16;
                        var6 = false;
                        this.selectedOptionView(var11, 1);
                    }

                    return;
                }

                return;
            }
        }

        var4 = 2131231201;
        if (var3 != null) {
            if (var3 == var4) {
                if (isAnswerNotSubmitDone) {
                    var16 = this.tvOptionTwo;
                    if (var16 != null) {
                        var11 = var16;
                        var6 = false;
                        this.selectedOptionView(var11, 2);
                    }

                    return;
                }

                return;
            }
        }

        var4 = 2131231200;
        if (var3 != null) {
            if (var3 == var4) {
                if (isAnswerNotSubmitDone) {
                    var16 = this.tvOptionThree;
                    if (var16 != null) {
                        var11 = var16;
                        var6 = false;
                        this.selectedOptionView(var11, 3);
                    }

                    return;
                }

                return;
            }
        }

        var4 = 2131231198;
        if (var3 != null) {
            if (var3 == var4) {
                if (isAnswerNotSubmitDone) {
                    var16 = this.tvOptionFour;
                    if (var16 != null) {
                        var11 = var16;
                        var6 = false;
                        this.selectedOptionView(var11, 4);
                    }

                    return;
                }

                return;
            }
        }

        var4 = 2131230820;
        if (var3 != null) {
            if (var3 == var4) {
                if (this.btnSubmit != null) {
                    var6 = false;
                    int var13;
                    ArrayList var15;
                    if (this.mSelectedOptionPosition == 0) {
                        if (this.isSelectedAnswer) {
                            this.isSelectedAnswer = false;
                            int var10001 = this.mCurrentPosition++;
                            var13 = this.mCurrentPosition;
                            var15 = this.mQuestionList;
                            Intrinsics.checkNotNull(var15);
                            if (var13 <= var15.size()) {
                                this.setQuestionList();
                            } else {
                                Intent intent = new Intent((Context)this, QuizResultActivity.class);
                                intent.putExtra("user_name", this.mUserName);
                                ArrayList var10002 = this.mQuestionList;
                                intent.putExtra("total_questions", (Serializable)(var10002 != null ? var10002.size() : null));
                                this.startActivity(intent);
                                this.finish();
                            }
                        } else {
                            Toast.makeText((Context)this, (CharSequence)"Please Select Answer", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        ArrayList var14 = this.mQuestionList;
                        QuizQuestion question = var14 != null ? (QuizQuestion)var14.get(this.mCurrentPosition - 1) : null;
                        if (question != null) {
                            boolean var10 = false;
                            this.answerView(this.mSelectedOptionPosition);
                            var13 = this.mCurrentPosition;
                            var15 = this.mQuestionList;
                            Intrinsics.checkNotNull(var15);
                            if (var13 == var15.size()) {
                                var10000 = this.btnSubmit;
                                if (var10000 != null) {
                                    var10000.setText((CharSequence)"FINISH");
                                }
                            } else {
                                var10000 = this.btnSubmit;
                                if (var10000 != null) {
                                    var10000.setText((CharSequence)"GO TO NEXT QUESTION");
                                }
                            }

                            this.mSelectedOptionPosition = 0;
                        }
                    }
                }
            }
        }

    }

    private final void answerView(int selectedOptionNum) {
        List options = CollectionsKt.listOf(new TextView[]{this.tvOptionOne, this.tvOptionTwo, this.tvOptionThree, this.tvOptionFour});
        int index = 0;

        for(Iterator var5 = ((Iterable)options).iterator(); var5.hasNext(); ++index) {
            TextView option = (TextView)var5.next();
            if (index + 1 != selectedOptionNum) {
                if (option != null) {
                    option.setBackground(ContextCompat.getDrawable((Context)this, R.drawable.default_option_border_bg));
                }
            }
        }

    }

    public QuizQuestionActivity() {
        Intent var10001 = this.getIntent();
        this.quizType = var10001 != null ? var10001.getIntExtra("quiz_type", 1) : 1;
    }
}
