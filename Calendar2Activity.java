public class Calendar2Activity extends AppCompatActivity {

    private static final String TAG = "Calendar2Activity";
    private TextView theDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);
        setTitle("                         DIARY");

        theDate = (TextView) findViewById(R.id.date);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

    }
}
