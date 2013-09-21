# ウォームアップクイズ
## maps API
分からなかったので、問題文をそのままHTMLファイルに埋め込んで地図を表示して確認しました。
<http://code.google.com/p/zenjiro-gdd11jp/source/browse/quiz.html>

# 分野別クイズ
## Web Game
ヒントどおりに解くのが何か嫌だったので、JavaのRobotで色を取得したりクリックしたりしました。
最初予想していたよりもカードの枚数が増えたので、調整がかなり面倒でした。
<http://code.google.com/p/zenjiro-gdd11jp/source/browse/com/wordpress/zenjiro/webgame/WebGame.java>

# Google Apps Script
とにかく開発環境が貧弱で辛かったです。変数名の補完、名前の変更、インライン化／展開、インデントのフォーマット、キーボードショートカットでの実行など、当たり前のことができませんでした。
もしかしたらEclipse用のプラグインがもうあるのかもしれませんが。
<http://code.google.com/p/zenjiro-gdd11jp/source/browse/AppsScript.js>

# チャレンジクイズ
## スライドパズル
4378/5000問解けて、採点結果も43.78点でした。Javaで実装しました。

3x3、4x4の問題はBrian S. Borowskiさん（<http://www.brian-borowski.com/Software/Puzzle/>）のソルバを壁対応させたもので最適解を求め、それ以外の問題は評価関数をキーにPriorityQueueに入れて1つずつ取り出すだけ＋盤面をHashSetに保存して再訪問を防ぐ、という適当な探索アルゴリズムで近似解を求めました。

マンハッタン距離の計算のときに0（空白）のパネル分も誤って加算していたバグを直したら急速に正解率が上がった気がします。
<http://code.google.com/p/zenjiro-gdd11jp/source/browse/com/wordpress/zenjiro/slidingpuzzle/BruteForceSolver.java>
