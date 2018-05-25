package com.example.nizer01.goplay.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.dao.AccountDao;
import com.example.nizer01.goplay.dao.ProfileDao;
import com.example.nizer01.goplay.domain.Account;
import com.example.nizer01.goplay.domain.Profile;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Todo: Inserir mais logs se nescessário.
 * Todo: Verficiar se e-mail é valido. (FEITO)
 * Todo: Verificar se data de nascimento é valida.
 */

/**
 * Classe responsável pela criação da atividade de registro.
 * Essa atividade tem como objetivo criar uma GUI para o usuário se registar no sistema.
 * Após o registro os dados serão salvos no banco de dados.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Tag usada para log.
     */
    private static final String TAG = "RegisterActivity";

    /**
     * Variáveis que serão inicializados pelo método setWidgets().
     */
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etRepeatPassword;
    private EditText etBirthday;
    private RadioGroup rgGender;
    private Button btRegister;
    private Button btBack;

    /**
     * Variável da autencicação via firebase.
     */
    private FirebaseAuth fbAuth = FirebaseAuth.getInstance();

    /**
     * Método onCreate() é inicializado automaticamente ao iniciar a atividade.
     *
     * @param savedInstanceState Parametro obrigatório para a criação da atividade.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setWidgets();
        setListeners();
    }

    /**
     * setWidgets() responsável por relacionar os objetos gráficos do layout com os objetos
     * desta classe declarados na inicialização.
     * É chamado apenas uma vez dentro do método onCreate().
     */
    private void setWidgets() {
        etFirstName = (EditText) findViewById(R.id.edittext_register_name);
        etLastName = (EditText) findViewById(R.id.edittext_register_lastname);
        etEmail = (EditText) findViewById(R.id.edittext_register_email);
        etPassword = (EditText) findViewById(R.id.edittext_register_password);
        etRepeatPassword = (EditText) findViewById(R.id.edittext_register_confirmpassword);
        etBirthday = (EditText) findViewById(R.id.edittext_register_birthday);
        rgGender = (RadioGroup) findViewById(R.id.radiogroup_register_gender);
        btRegister = (Button) findViewById(R.id.button_register_register);
        btBack = (Button) findViewById(R.id.button_register_back);
    }

    /**
     * setListeners() responsável por criar e relacionar os listeners utilizados nessa atividade
     * com seus respectivos botoes.
     * É chamado apenas uma vez dentro do método onCreate().
     */
    private void setListeners() {
        etBirthday.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        btBack.setOnClickListener(this);
    }

    /**
     * onClick() é responsável por detectar em qual botão o listener foi chamado utilizando o id do
     * widget como parâmetro e direcionar para o método correto que irá tratar o clique.
     *
     * @param v Recebe a View que está sendo executada no momento e solicita a id do widget clicado
     *          através do método getId().
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edittext_register_birthday:
                onClickEdittextBirthday();
                break;
            case R.id.button_register_register:
                onClickButtonRegister();
                break;
            case R.id.button_register_back:
                onClickButtonBack();
                break;
        }
    }

    /**
     * onClickEdittextBirthday() é responsável por tratar o clique no campo de selecionar a data
     * de nascimento no ato do registro.
     * Ele abre um datePicketDialog para o usuário e ao selecionar uma data o campo da data de
     * nascimento é preenchido com a data selecionada.
     * É chamado apenas pelo método onClick().
     */
    private void onClickEdittextBirthday() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    etBirthday.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /**
     * onClickButtonRegister() é responsável por tratar o clique no botão de finalizar o registro
     * do usuário e verificar se os dados estão corretos.
     * Todos os textos nos EditText são passados para strings.
     * Caso alguma string esteja vazia, uma mensagem é disparada para o usuário.
     * Caso as strings estejam preenchidas, é verificado se os passwords são idênticos, caso não
     * sejam, uma mensagem é disparada ao usuário.
     * Caso as duas verificações acima sejam aceitas o método createAccount() da classe AccountDao
     * é chamado e uma mensagem de sucesso é disparada pasa o usuário.
     * É chamado apenas pelo método onClick().
     */
    private void onClickButtonRegister() {
        String firstname = etFirstName.getText().toString();
        String lastname = etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String passwordrepeat = etRepeatPassword.getText().toString();
        String birthday = etBirthday.getText().toString();
        RadioButton selectedRadioButton = (RadioButton) findViewById(
                rgGender.getCheckedRadioButtonId());
        String gender = selectedRadioButton == null ? "" : selectedRadioButton.getText().toString();
        if (!firstname.equals("") &&
                !lastname.equals("") &&
                !email.equals("") &&
                !password.equals("") &&
                !passwordrepeat.equals("") &&
                !birthday.equals("") &&
                !gender.equals("")) {
            if (password.equals(passwordrepeat)) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    createAccountObject(email, password);
                    createProfileObject(firstname, lastname, birthday, gender);
                }else{
                    Toast.makeText(this, "Invalid e-Mail", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, R.string.toast_passwordsdifferent,
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.toast_emptyfield, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * onClickButtonBack() finaliza a atividade caso o usuário clique no botão de voltar, neste caso
     * voltando para a tela inicial de login.
     * É chamado apenas pelo método onClick().
     */
    private void onClickButtonBack() {
        finish();
    }

    private void createAccountObject(String email, String password){
        Account account = new Account(email, password);
        AccountDao.createAccount(account);
    }

    private void createProfileObject(String firstname, String lastname, String birthday, String gender) {
        Profile profile = new Profile();
        profile.setFirstName(firstname);
        profile.setLastName(lastname);
        try {
            DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
            Date date = df.parse(birthday);
            long time = date.getTime();
            profile.setBirthDate(new Timestamp(time));
        } catch (ParseException e) {
            Log.e(TAG, "createUserObject: ", e);
        }
        profile.setGender(gender);
        ProfileDao.createProfile(profile);
        finish();
    }

}
