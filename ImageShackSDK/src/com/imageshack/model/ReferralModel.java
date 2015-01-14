package com.imageshack.model;

public class ReferralModel extends AbstractModel {

    private String url;

    public ReferralModel() {
        success = true;
        error = null;
        processTime = 0;
        url = null;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the processTime
     */
    public int getProcessTime() {
        return processTime;
    }

    /**
     * @param processTime
     *            the processTime to set
     */
    public void setProcessTime(int processTime) {
        this.processTime = processTime;
    }

    /**
     * @return the referral URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param referral
     *            the referral to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ReferralModel [url = " + url + ", success = " + success + ", error = " + error
                + ", processTime=" + processTime + "]";
    }

}
