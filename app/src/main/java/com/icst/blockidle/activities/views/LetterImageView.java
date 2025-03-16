package com.icst.blockidle.activities.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LetterImageView extends View {

	private char letter = 'A'; // Default letter
	private Paint circlePaint;
	private Paint textPaint;
	private final Rect textBounds = new Rect();

	public LetterImageView(Context context) {
		super(context);
		init();
	}

	public LetterImageView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LetterImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		circlePaint.setColor(Color.WHITE); // Background circle color

		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setColor(Color.DKGRAY); // Text color
		textPaint.setTextSize(60f); // Adjust as needed
		textPaint.setTextAlign(Paint.Align.CENTER);
	}

	public void setLetter(char letter) {
		this.letter = letter;
		invalidate(); // Redraw the view
	}

	public void setCircleColor(int color) {
		circlePaint.setColor(color);
		invalidate();
	}

	public void setTextColor(int color) {
		textPaint.setColor(color);
		invalidate();
	}

	public void setTextSize(float size) {
		textPaint.setTextSize(size);
		invalidate();
	}

	@Override
	protected void onDraw(@NonNull Canvas canvas) {
		super.onDraw(canvas);

		int width = getWidth();
		int height = getHeight();
		int radius = Math.min(width, height) / 2;

		// Draw circle
		canvas.drawCircle(width / 2f, height / 2f, radius, circlePaint);

		// Draw letter in center
		String text = String.valueOf(letter).toUpperCase();
		textPaint.getTextBounds(text, 0, 1, textBounds);
		float textHeight = textBounds.height();

		canvas.drawText(text, width / 2f, height / 2f + textHeight / 2f, textPaint);
	}
}
