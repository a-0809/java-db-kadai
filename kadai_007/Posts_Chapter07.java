package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement statement = null;
		
		try {
		//データベースの接続
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost/challenge_java",
				"root",
				"Askl1290@"
				);
		
		System.out.println("データベース接続成功：" + con);
		
		//Statement オブジェクトを生成
		statement = con.createStatement();
		
		//(データ追加) SQLクエリを準備
		String sql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES"
				+ "('1003', '2023-02-08', '昨日の夜は徹夜でした・・	', '	13'),"
				+ "('1002', '2023-02-08', 'お疲れ様です！', '12'),"
				+ "('1003', '2023-02-09', '今日も頑張ります！', '18'),"
				+ "('1001', '2023-02-09', '無理は禁物ですよ！', '17'),"
				+ "('1002', '2023-02-10', '明日から連休ですね！', '20')";
		
		//SQLクエリを実行（DBMSに送信）
		System.out.println("レコード追加を実行します");
		int rowCnt = statement.executeUpdate(sql);
		System.out.println( rowCnt + "件のレコードが追加されました");
		
		//(データ検索) SQLクエリを準備
		sql = "SELECT * FROM posts WHERE user_id = 1002";
		
		//SQLクエリを実行（DBMSに送信）
		ResultSet result = statement.executeQuery(sql);
		System.out.println("ユーザーIDが1002のレコードを検索しました");
		
		//SQLクエリの実行結果を抽出
		while(result.next()) {
			Date postedAt = result.getDate("posted_at");
			String postContent = result.getString("post_content");
			int liles = result.getInt("likes");
			System.out.println(result.getRow() + "件目：投稿日時=" + postedAt + "／投稿内容=" + postContent + "／いいね数=" + liles);
			}
		
		} catch(SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			//使用したオブジェクトを解放
			if( statement != null) {
				try { statement.close(); } catch (SQLException ignore) {}
				}
			if(con != null) {
				try { con.close(); } catch (SQLException ignore) {}
				}
			}
		}
	}

