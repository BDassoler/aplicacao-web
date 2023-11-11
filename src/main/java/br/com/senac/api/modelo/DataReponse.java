package br.com.senac.api.modelo;

public class DataReponse {
    private Object rows;
    private InfoRow infoRows;

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public InfoRow getInfoRows() {
        return infoRows;
    }

    public void setInfoRows(InfoRow infoRows) {
        this.infoRows = infoRows;
    }
}
