package com.example.takeuchi.webview1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.KeyEvent;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 以下追加
		final WebView  myWebView = (WebView)findViewById(R.id.webView1);
		//標準ブラウザをキャンセル
		myWebView.setWebViewClient(new WebViewClient());
		//アプリ起動時に読み込むURL
		//myWebView.loadUrl("https://ideacloud.co.jp/dev/");
//		myWebView.loadUrl("http://192.168.222.186");
		myWebView.loadUrl("http://192.168.222.186/test/test.html");

		// HTMLのmetaのViewportを反映させる
		myWebView.getSettings().setUseWideViewPort(true);
		myWebView.getSettings().setJavaScriptEnabled(true);
		// Javascriptを有効にする
		myWebView.getSettings().setLoadWithOverviewMode(true);
		// スクロールバーを無くす
//		myWebView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);







		/*
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show();
			}
		});
		*/
	}




	public void onButtonClick(View view){
		// ここで表示しているhtmlの値が取れたらいいな



		Uri uri = Uri.parse("http://192.168.222.186/adimg/1.mp4");
		switch (view.getId()) {
			case R.id.button1:
				// 暗黙的インテントの場合
				Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
				intent1.setDataAndType(uri, "video/mp4");
				startActivity(intent1);
				break;
			case R.id.button2:
//				Log.v("ボタン押されました","ボタン押されました");
				// 明示的インテントの場合
				Intent intent2 = new Intent();
				//intent2.setClassName("org.xbmc.kodi", "org.xbmc.kodi.Splash");
				// http://web-terminal.blogspot.jp/2013/04/intentactivity.html ※参照
				//intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				//intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				//intent2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				// 起動時にスタックに追加しない（これだと毎回再生はできる）
				intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

				//intent2.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
				//intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

				intent2.setClassName("org.xbmc.kodi", "org.xbmc.kodi.Splash");


				intent2.setDataAndType(uri, "video/mp4");

				/*
				startActivity(intent2);
				finish();
*/
				// 結果を貰うにはこれ必須
				startActivityForResult(intent2, 1000);

				Log.d("IntentSample", "明示的");
				break;
			case R.id.buttonSo:
				Log.d("IntentSample", "Soボタンだぞ");


				try {

					URI url = new URI("ws://127.0.0.1:9090/jsonrpc");

					try {
						//WebSocketFactory factory = new WebSocketFactory();
						/*
						WebSocket ws = new WebSocketFactory().createSocket(url);
						ws.connect();
						*/

Log.d("IntentSample", "通ったの？");




					} catch(IOException e) {

					}

				} catch (URISyntaxException e){

				}

				break;
			default:
				break;
		}
	}


	// 戻りを求めてみる
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.d("IntentSample", "----------------");
		Log.d("IntentSample", Integer.toString(requestCode)); // 渡した値が来る
		Log.d("IntentSample", Integer.toString(resultCode)); // KODIをいじれないので0が来る
		Log.d("IntentSample", "================");
		// 返り値の取得
		//if (requestCode == MY_INTENT_BROWSER) {
			if (resultCode == RESULT_OK) {
				// Success
				Log.d("IntentSample", "success");
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
				Log.d("IntentSample", "canceled");

			}
		//}
	}



	public boolean onKeyDown(int keyCode, KeyEvent event) {
		WebView  myWebView = (WebView)findViewById(R.id.webView1);
		// 端末のBACKキーで一つ前のページヘ戻る
		if(keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
			myWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode,  event);
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
