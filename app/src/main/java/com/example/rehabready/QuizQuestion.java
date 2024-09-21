package com.example.rehabready;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.Intrinsics;


public final class QuizQuestion {
    private final int id;
    @NotNull
    private final String question;
    @NotNull
    private final String optionOne;
    @NotNull
    private final String optionTwo;
    @NotNull
    private final String optionThree;
    @NotNull
    private final String optionFour;

    public final int getId() {
        return this.id;
    }

    @NotNull
    public final String getQuestion() {
        return this.question;
    }

    @NotNull
    public final String getOptionOne() {
        return this.optionOne;
    }

    @NotNull
    public final String getOptionTwo() {
        return this.optionTwo;
    }

    @NotNull
    public final String getOptionThree() {
        return this.optionThree;
    }

    @NotNull
    public final String getOptionFour() {
        return this.optionFour;
    }

    public QuizQuestion(int id, @NotNull String question, @NotNull String optionOne, @NotNull String optionTwo, @NotNull String optionThree, @NotNull String optionFour) {
        super();
        Intrinsics.checkNotNullParameter(question, "question");
        Intrinsics.checkNotNullParameter(optionOne, "optionOne");
        Intrinsics.checkNotNullParameter(optionTwo, "optionTwo");
        Intrinsics.checkNotNullParameter(optionThree, "optionThree");
        Intrinsics.checkNotNullParameter(optionFour, "optionFour");
        this.id = id;
        this.question = question;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
    }

    public final int component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.question;
    }

    @NotNull
    public final String component3() {
        return this.optionOne;
    }

    @NotNull
    public final String component4() {
        return this.optionTwo;
    }

    @NotNull
    public final String component5() {
        return this.optionThree;
    }

    @NotNull
    public final String component6() {
        return this.optionFour;
    }

    @NotNull
    public final QuizQuestion copy(int id, @NotNull String question, @NotNull String optionOne, @NotNull String optionTwo, @NotNull String optionThree, @NotNull String optionFour) {
        Intrinsics.checkNotNullParameter(question, "question");
        Intrinsics.checkNotNullParameter(optionOne, "optionOne");
        Intrinsics.checkNotNullParameter(optionTwo, "optionTwo");
        Intrinsics.checkNotNullParameter(optionThree, "optionThree");
        Intrinsics.checkNotNullParameter(optionFour, "optionFour");
        return new QuizQuestion(id, question, optionOne, optionTwo, optionThree, optionFour);
    }

    // $FF: synthetic method
    public static QuizQuestion copy$default(QuizQuestion var0, int var1, String var2, String var3, String var4, String var5, String var6, int var7, Object var8) {
        if ((var7 & 1) != 0) {
            var1 = var0.id;
        }

        if ((var7 & 2) != 0) {
            var2 = var0.question;
        }

        if ((var7 & 4) != 0) {
            var3 = var0.optionOne;
        }

        if ((var7 & 8) != 0) {
            var4 = var0.optionTwo;
        }

        if ((var7 & 16) != 0) {
            var5 = var0.optionThree;
        }

        if ((var7 & 32) != 0) {
            var6 = var0.optionFour;
        }

        return var0.copy(var1, var2, var3, var4, var5, var6);
    }

    @NotNull
    public String toString() {
        return "Question(id=" + this.id + ", question=" + this.question + ", optionOne=" + this.optionOne + ", optionTwo=" + this.optionTwo + ", optionThree=" + this.optionThree + ", optionFour=" + this.optionFour + ")";
    }

    public int hashCode() {
        int var10000 = Integer.hashCode(this.id) * 31;
        String var10001 = this.question;
        var10000 = (var10000 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.optionOne;
        var10000 = (var10000 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.optionTwo;
        var10000 = (var10000 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.optionThree;
        var10000 = (var10000 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.optionFour;
        return var10000 + (var10001 != null ? var10001.hashCode() : 0);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof QuizQuestion) {
                QuizQuestion var2 = (QuizQuestion)var1;
                if (this.id == var2.id && Intrinsics.areEqual(this.question, var2.question) && Intrinsics.areEqual(this.optionOne, var2.optionOne) && Intrinsics.areEqual(this.optionTwo, var2.optionTwo) && Intrinsics.areEqual(this.optionThree, var2.optionThree) && Intrinsics.areEqual(this.optionFour, var2.optionFour)) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}
