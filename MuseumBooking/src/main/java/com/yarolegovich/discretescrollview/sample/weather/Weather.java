package com.yarolegovich.discretescrollview.sample.weather;

/**
 * Created by yarolegovich on 08.03.2017.
 */

public enum Weather {

    PERIODIC_CLOUDS("Hill Palace is the largest archeological museum in Kerala, located at Tripunithura, Kochi. It was the imperial administrative office and official residence of Cochin Maharaja.Built in 1865, the palace complex consists of 49 buildings in the traditional architectural style, spreading across 54 acres (220,000 m2). The complex has an archaeological museum, a heritage museum, a deer park, a pre-historic park and a children’s park.[1] The campus of the museum is home to several rare species of medicinal plants. Presently the palace has been converted into a museum by The Kerala State Archaeology Department and is open to public. The palace is about 10 kilometres (6.2 mi) from the city centre and is approachable by road and rail. \n" +
            "Visiting Hours: 10:00 - 17:00 hours on all days except Mondays."),
    CLOUDY("Named after the former Governor General of Madras, John Napier, this museum is one of the finest examples of architecture that blends Indian, Chinese, Kerala and Mughal styles.   From gothic roof to minarets, all elements combine seamlessly to form this unique masterpiece. A landmark in the city of Thiruvananthapuram, it was designed by Robert Chisholm and the construction of the building was completed in 1880. The Indo-Saracenic structure also boasts of a unique natural air conditioning system. The museum is home to a rare collection of archaeological and historic artefacts, a temple chariot, bronze idols, ancient ornaments and ivory carvings. It also has the Sree Chitra Art Gallery, which contains paintings by the modern master Raja Ravi Varma and the landscape painter, Nicholas Roerich apart from Mughal and Tanjore art.\n" +
            "Visiting Hours: 10:00 - 17:00 hours on all days except Mondays."),
    MOSTLY_CLOUDY("Among the more picturesque and historically-relevant sites in all of Thrissur is its famous Archaeological Museum. Originally in the Kollengode Palace building, it has now been moved to the Shakthan Thampuran Palace.This beautiful building once was the seat of the Perumpadappu Swaroopam, the former ruling dynasty of Kochi, and now houses some of the finest murals from across God’s Own Country. Step inside and one is immediately made aware of the unique treasures this place holds, including Veerakallu, temple models, olagrandhangal (manuscripts on dry palm leaves) and megaliths. Veerkallu (herostones), especially, catch the intrigue of all new visitors. These stone engravings and sculptures of figures and weapons belong to a bygone era, and were found in the forests of Thrissur and Wayanad .\n" +
            "Visiting Hours: 10:00 - 17:00 hours on all days except Mondays."),
    PARTLY_CLOUDY("122 smiling wooden horses await all who visit ‘Kuthiramalika’, which means “palace of horses”. Formally known as Kuthiramalika Palace Museum or Puthenmalika Palace Museum, it is a pristine two-storied palace near the Sree Padmanabhaswamy Temple in Thiruvananthapuram.Built by Swathi Thirunal Balarama Varma, who was Maharaja of the Kingdom of Travancore in British India, it takes one back to the 1840s when it was first constructed. It is a great example of the Kerala school of architecture and is made from teakwood, rosewood, marble, and granite. The museum houses idols and sculptures made from white marble, Kathakali figures, Belgian mirrors and paintings. The flourishing spice trade between Kerala and the world made it possible to obtain those items.  \n" +
            "Visiting Hours: 10:00 - 17:00 hours on all days except Mondays."),
    CLEAR("The Museum of Kerala History portrays the history of Kerala from its early inhabitants to the modern era. There are 38 life-size tableaux, each accompanied by a variety of visuals, reflecting the cultural and social history of Kerala. There is also a gallery that has on display paintings and sculptures, more than 200 in number by some of India's leading modern masters, including Raja Ravi Varma, There is also a gallery that has on display paintings and sculptures, more than 200 in number by some of India's leading modern masters, including Raja Ravi Varma, M.F. Husain, F.N. Souza, Jamini Roy, Benode Behari Mukherjee, Ramkinker Baij, Ram Kumar and K.G. Subramanyan, among others. Both English and Malayalam, the sound and light shows will tell you all you need to know about Kerala’s history.\n" +
            "Visiting Hours: 10:00 - 17:00 hours on all days except Mondays.");

    private String displayName;

    Weather(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
