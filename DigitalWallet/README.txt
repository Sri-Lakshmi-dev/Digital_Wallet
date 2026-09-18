MY SIMPLE DIGITAL WALLET
This is a beginner Java project that simulates a very simple digital wallet.
You can deposit money, withdraw money, and see your transaction history.
It has a simple window (GUI) made with Java Swing, so no typing commands
inside the app itself - just click buttons.

PROJECT FILES
src/Main.java         -> Starts the program and shows the window
src/Wallet.java        -> Keeps track of balance and transactions
src/Transaction.java   -> Represents one deposit or withdraw
wallet_data.txt        -> Created automatically to save your balance and
 history, so it's still there next time you open it

HOW TO RUN IT
1. Open the "src" folder in your Java IDE (like IntelliJ, Eclipse, or VS Code)
   as a project, OR compile it yourself if you know how to use javac/java.
2. Run the Main.java file.
3. A window will pop up. Type an amount and click Deposit or Withdraw.
4. Your balance and history will update automatically and save to
   wallet_data.txt so your data isn't lost when you close the app.

NOTES
- This is a learning project, not a real banking app. There is no
  internet connection, login system, or real money involved.
- Feel free to change things like colors, fonts, or add new features
  such as multiple accounts or transaction categories.
