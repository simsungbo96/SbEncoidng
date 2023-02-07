package com.sbsj.encodingprogram;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "인코딩";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

     /*    2023.02.07 MD_심성보
         Encoding 일괄 프로그램 사용방법
         1. 액셀에다가 =CHAR(34)&셀이름&CHAR(34)&"," 사용하여 컬럼이 자동으로 쌍따옴표와 콤마가 나오도록 만든다
         2. 가져와서 beforeLetter 안에 복붙한다.
         3. Run 누른다
         4. Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) 해당경로에 오늘날짜시간 파일이름(SbEncoding_202302070111)으로 저장된다.
         5. 끝
    */

        String[] beforeLetter = {"江原道襄陽郡降峴面洛山寺路73 ",
                "江原道襄陽郡降峴面沕淄川路70-９",
                "江原道襄陽郡西面大靑峰ギル",
                "江原道襄陽郡巽陽面東海大路2338、襄陽国際空港ホテル",
                "江原道襄陽郡襄陽邑彌勒ギル(ヤンウ・ネアンエ)",
                "江原道襄陽郡襄陽邑林泉1ギル(襄陽コアル)",
                "江原道襄陽郡襄陽邑ハンゴゲギル(eピョンハンセサン襄陽アパート)",
                "江原道襄陽郡縣北面ソンイ路86-32",
                "京畿道安城市古三面古三湖水路21、古三面事務所",
                "京畿道安城市大德面西東大路4,725(ロッテキャッスル・セントラルシティー)",
                "京畿道安城市三竹面三竹路112、三竹面事務所",
                "京畿道安城市瑞雲面瑞雲中央ギル16、瑞雲農業協同組合",
                "京畿道安城市陽城面邑內ギル16、陽城農業協同組合",
                "京畿道安養市東安區京水大路884番ギル12、地階(飛山洞、坪村レミアンプルジオ)",
                "京畿道安養市萬安區博達路470、博達2洞行政福祉センター(博達洞)",
                "京畿道平澤市細橋6路(細橋洞、ヒルステート平澤)",
                "慶尙南道咸陽郡咸陽邑咸陽路1206-71(咸陽ウェルガー・セントビュー)",
                "釜山廣域市江西區鳴旨國際7路133(鳴旨洞、中興S-クラスプラジウム)",
                "釜山廣域市江西區鳴旨國際7路37(鳴旨洞、ザシャープ鳴旨ファーストワールド2団地)",
                "ソウル特別市江北區道峰路333、ジョンウビル(水踰洞)",

        };
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fileName = "SbEncoding_";
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        //원하는 데이터 포맷 지정
        String strNowDate = simpleDateFormat.format(nowDate);
        fileName = fileName+strNowDate;
        File storeDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName);

        // File객체 생성
        if (!storeDir.exists()) { // 파일이 존재하지 않으면
            try {
                storeDir.createNewFile(); // 신규생성
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // BufferedWriter 생성
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(storeDir, true));
            for (String s : beforeLetter) {
                writer.append(getBase64encode(s));
                writer.newLine();
            }
            // 버퍼 및 스트림 뒷정리
            writer.flush(); // 버퍼의 남은 데이터를 모두 쓰기
            writer.close(); // 스트림 종료
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBase64encode(String content) {
        return Base64.encodeToString(content.getBytes(), Base64.NO_WRAP); //TODO Base64 암호화된 문자열로 반환
    }
}