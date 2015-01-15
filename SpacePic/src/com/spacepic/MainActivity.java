package com.spacepic;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	IodtHandler handler = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		handler = new IodtHandler();
		handler.processFeed();

		new MyTask().execute();
	}

	private void resetDisplay(String title, String date, Bitmap bitmap,
			String stringBuffer) {
		TextView titleView = (TextView) findViewById(R.id.textView1);
		titleView.setText(title);

		TextView dateView = (TextView) findViewById(R.id.textView2);
		dateView.setText(date);

		ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
		imageView.setImageBitmap(bitmap);

		TextView descriptionView = (TextView) findViewById(R.id.textView3);
		descriptionView.setText(stringBuffer);

	}

	public class MyTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			handler.processFeed();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			resetDisplay(handler.getTitle(), handler.getDate(),
					handler.getImage(), handler.getDescription());
			super.onPostExecute(result);
		}
	}
}
