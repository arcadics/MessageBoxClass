# MessageBoxClass
Java Message box test

Пример использования:

	public class TestMsg extends Fragment implements MessageBox.IMessageBox{
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
	}

