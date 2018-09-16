package com.arcadics.testlistctrl.dlg;

/*MessageBox класс.
Пример использования:
public class TestMsg extends FragmentBase implements MessageBox.IMessageBox{
	@Override
	public void onClick(View v) {
		MessageBox msg = new MessageBox("Ипорт данных", "Загрузить или обновить базу данных?", "ImportData");
		msg.setDialogClickListener(TestMsg.this);
		FragmentManager fm = getFragmentManager();
		if (fm != null) {
			msg.show(fm, "ImportData");
		}
	}
	@Override
    public void OnDialogResultListener(String dlgTag, int dialogResult) {
        Toast toast;

        if (dlgTag.equals("ImportData")) {
            if (dialogResult == MessageBox.DialogResultOK) {
                toast = Toast.makeText(getActivity().getApplicationContext(),
                        "Запущена процедура импорта данных", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}*/

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

@SuppressLint("ValidFragment")
public class MessageBox extends DialogFragment{

    public interface IMessageBox{
        void OnDialogResultListener(String dlgTag, int dialogResult);
    }

    public static int DialogResultOK = 1;
    public static int DialogResultCancel = 0;

    private String title;
    private String message;
    private String tag;

    private MessageBox.IMessageBox mInputListener;

    private int result;

    @SuppressLint("ValidFragment")
    public MessageBox(String title, String message, String dlgTag) {
       this.title = title;
       this.message = message;
       this.tag = dlgTag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String button1String = "ОК";
        String button2String = "Отмена";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);  // заголовок
        builder.setMessage(message); // сообщение
        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                result = DialogResultOK;

                if (mInputListener != null) {
                    mInputListener.OnDialogResultListener(tag, result);
                }
            }
        });
        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                result = DialogResultCancel;

                if (mInputListener != null) {

                    mInputListener.OnDialogResultListener(tag, result);
                }
            }
        });
        builder.setCancelable(true);

        return builder.create();
    }

    public void setDialogClickListener(MessageBox.IMessageBox listener){
        try{
            mInputListener = listener;
        }catch (ClassCastException e){
            String LOG_TAG = "TestMsgBox";
            Log.d(LOG_TAG, "onAttach: ClassCastException " + e.getMessage());
        }
    }
}
