
package client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TimeOfDayResponse_QNAME = new QName("http://service/", "timeOfDayResponse");
    private final static QName _TimeOfDay_QNAME = new QName("http://service/", "timeOfDay");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TimeOfDayResponse }
     * 
     */
    public TimeOfDayResponse createTimeOfDayResponse() {
        return new TimeOfDayResponse();
    }

    /**
     * Create an instance of {@link TimeOfDay }
     * 
     */
    public TimeOfDay createTimeOfDay() {
        return new TimeOfDay();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeOfDayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "timeOfDayResponse")
    public JAXBElement<TimeOfDayResponse> createTimeOfDayResponse(TimeOfDayResponse value) {
        return new JAXBElement<TimeOfDayResponse>(_TimeOfDayResponse_QNAME, TimeOfDayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeOfDay }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "timeOfDay")
    public JAXBElement<TimeOfDay> createTimeOfDay(TimeOfDay value) {
        return new JAXBElement<TimeOfDay>(_TimeOfDay_QNAME, TimeOfDay.class, null, value);
    }

}
