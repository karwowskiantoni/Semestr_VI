package model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TEXT")
@XmlAccessorType(XmlAccessType.FIELD)
public class Text {
  @XmlElement(name = "TITLE")
  private String TITLE;

  @XmlElement(name = "DATELINE")
  private String DATELINE;

  @XmlElement(name = "BODY")
  private String BODY;

  public String getTITLE() {
    return TITLE;
  }

  public void setTITLE(String TITLE) {
    this.TITLE = TITLE;
  }

  public String getDATELINE() {
    return DATELINE;
  }

  public void setDATELINE(String DATELINE) {
    this.DATELINE = DATELINE;
  }

  public String getBODY() {
    return BODY;
  }

  public void setBODY(String BODY) {
    this.BODY = BODY;
  }
}
