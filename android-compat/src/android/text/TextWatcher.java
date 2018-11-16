package android.text;

public abstract class TextWatcher {
    public abstract void beforeTextChanged(CharSequence s, int start, int count, int after);

    public abstract void onTextChanged(CharSequence s, int start, int before, int count);

    public abstract void afterTextChanged(Editable s);
}
