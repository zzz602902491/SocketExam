1

mvn exec:java -Dexec.mainClass="com.hand.Exam1.App"

2

Server:
mvn exec:java -Dexec.mainClass="com.hand.Exam2.PDFServer"

Client:
mvn exec:java -Dexec.mainClass="com.hand.Exam2.PDFClient"

3

mvn exec:java -Dexec.mainClass="com.hand.Exam3.App"

第二个程序的PDF文件在第一个程序的根目录下，所以需要运行第一道题的程序，两个程序的文件夹需要放在同一目录下，因为第二个程序的文件路径为"../Exam1/target.pdf"
