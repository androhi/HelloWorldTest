package com.example.helloworld.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.example.helloworld.MainActivity;

/**
 * Activityテストケース・クラスのActivityInstrumentationTestCase2<T>を継承しています。
 * このクラスを、テストのActivityクラスでパラメータ化します。
 * @author Shimokawa
 *
 */
public class HelloWorldTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	private static final String TAG = HelloWorldTest.class.getSimpleName();
	
	private MainActivity mActivity;
	private TextView mView;
	private String resourceString;
	
	/**
	 * テストケースのコンストラクタは、テストを実行する時、Androidテスト・フレームワークによって利用されます。
	 * パラメータ付きで、親クラスのコンストラクタを呼び、フレームワークにどのAndroidアプリがテストされるのかを伝えます。
	 */
	public HelloWorldTest() {
		super("com.example.helloworld", MainActivity.class);
	}
	
	/**
	 * JUnitのsetUp()メソッドをオーバーライドします。
	 * このメソッドは、それぞれのテスト・メソッドが実行されるのに先立って、Androidテスト・フレームワークによって呼ばれます。
	 * setUp()メソッドは、変数を初期化しテスト環境を準備するために利用します。
	 * ここでは、以下の3つを行っています。
	 * 1. HelloWorldアプリの起動
	 * 2. 画面に表示されるテキスト・ウィジェットの取得
	 * 3. リソースファイル内のテキストの取得
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = this.getActivity();
		mView = (TextView)mActivity.findViewById(com.example.helloworld.R.id.textview);
		resourceString = mActivity.getString(com.example.helloworld.R.string.hello_world);
	}
	
	/**
	 * 前提条件テストを定義します。
	 * このメソッドは、他のテストの実行に先立ってアプリの前提条件をチェックします。
	 * setUp()メソッドと違い、一度しか実行されないのでオーバーヘッドが少ないです。
	 * ここでは、以下の2つのみ行っています。
	 * 1. テスト下のアプリが正しく初期化されているか
	 * 2. ターゲットのTextView領域が存在しているか
	 */
	public void testPreconditions() {
		// nullでない時に限って、このテストは成功します。
		assertNotNull(mView);
	}
	
	/**
	 * 簡単なユニット・テストを定義します。
	 * このメソッドは、JUnit Assertメソッドを呼び出して、ターゲットのTextViewが期待されているテキストを表示しているかをチェックします。
	 * ここで期待されているのは、HelloWorldのactivity_main.xmlファイルの中で宣言され、リソースID：hello_worldで
	 * 参照されているstringリソースを表示していると期待します。
	 * assertEquals(String,String)の呼び出しは、hello_worldリソースから直接読みだした期待される値と、
	 * TextViewのgetText()メソッドから得られたTextViewで表示されるテキストとを比較します。
	 */
	public void testText() {
		// 文字列が一致した場合に限り、このテストは成功します。
		assertEquals(resourceString, (String)mView.getText());
	}
}
