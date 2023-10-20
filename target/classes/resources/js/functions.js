//-------------- mascara para moeda -->

function mascara(o, f) {
    v_obj = o;
    v_fun = f;
    setTimeout("execmascara()", 1);
}

function execmascara() {
    v_obj.value = v_fun(v_obj.value);
}

function valor(v) {
    v = v.replace(/\D/g, "");
    v = v.replace(/[0-9]{15}/, "invalido");
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1.$2"); // coloca virgula antes dos ultimos 2 digitos  
    return v;
}
//----------------------------------- mascara para moeda -->