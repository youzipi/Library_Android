package com.youzipi.topbar_demo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by youzipi on 2015/4/27.
 */
public class Topbar extends RelativeLayout{

    private Button leftButton,rightButton;
    private TextView tvTitle;

    private int leftColor;
    private Drawable leftBackground;
    private String leftText;

    private int rightColor;
    private Drawable rightBackground;
    private String rightText;

    private float titleSize;
    private int titleColor;
    private String titleText;

    private LayoutParams leftParams,rightParams,titleParams;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.topbar);
        leftColor = ta.getColor(R.styleable.topbar_leftColor,0);
        leftBackground = ta.getDrawable(R.styleable.topbar_leftBackground);
        leftText = ta.getString(R.styleable.topbar_leftText);

        rightColor = ta.getColor(R.styleable.topbar_rightColor,0);
        rightBackground = ta.getDrawable(R.styleable.topbar_rightBackground);
        rightText = ta.getString(R.styleable.topbar_rightText);

        titleSize = ta.getDimension(R.styleable.topbar_titleTextSize,0);
        titleColor = ta.getColor(R.styleable.topbar_titleTextColor,0);
        titleText = ta.getString(R.styleable.topbar_titleText);

        ta.recycle();

        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);

        leftButton.setTextColor(leftColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);

        rightButton.setTextColor(rightColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);

        tvTitle.setTextColor(titleColor);
        tvTitle.setTextSize(titleSize);
        tvTitle.setText(titleText);
        tvTitle.setGravity(Gravity.CENTER);

        //setBackgroundColor(0xFFF59563);

        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);

        addView(leftButton,leftParams);

        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);

        addView(rightButton,rightParams);


        titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);

        addView(tvTitle,titleParams);
    }
}
