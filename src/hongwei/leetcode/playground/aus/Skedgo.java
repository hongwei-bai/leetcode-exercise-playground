package hongwei.leetcode.playground.aus;

import hongwei.leetcode.playground.common.Log;
import hongwei.leetcode.playground.IQuestion;

import java.util.*;

/*
We would like to send you a coding challenge:
Provided 2 strings, return 2 character iterables (Iterable<Character> in Java) such that:
- The first Iterable iterates over the characters that exist in the first string but not in the second string
- The second Iterable iterates over the characters that exist in the second string but not in the first string

You can implement it your preferred language and submit to us as email attachments. Please also clarify any assumptions that you make.
 */
public class Skedgo implements IQuestion {
    @Override
    public void run() {
        String s1 = "The British Broadcasting Corporation (BBC) is a British public service broadcaster and in the form of the BBC World Service an instrument of British foreign policy, formerly funded by the Foreign and Commonwealth Office[4][5] until 1 April 2014.[6] Its headquarters are at Broadcasting House in Westminster, London, and it is the world's oldest national broadcasting organisation[7] and the largest broadcaster in the world by number of employees. It employs over 20,950 staff in total, 16,672 of whom are in public sector broadcasting.[8][9][10][11][12] The total number of staff is 35,402 when part-time, flexible, and fixed-contract staff are included.[13]\n" +
                "\n" +
                "The BBC is established under a Royal Charter[14] and operates under its Agreement with the Secretary of State for Digital, Culture, Media and Sport.[15] Its work is funded principally by an annual television licence fee[16] which is charged to all British households, companies, and organisations using any type of equipment to receive or record live television broadcasts and iPlayer catch-up.[17] The fee is set by the British Government, agreed by Parliament,[18] and used to fund the BBC's radio, TV, and online services covering the nations and regions of the UK. Since 1 April 2014, it has also funded the BBC World Service (launched in 1932 as the BBC Empire Service), which broadcasts in 28 languages and provides comprehensive TV, radio, and online services in Arabic and Persian.\n" +
                "\n" +
                "Around a quarter of BBC revenues come from its commercial arm BBC Studios Ltd (formerly BBC Worldwide), which sells BBC programmes and services internationally and also distributes the BBC's international 24-hour English-language news services BBC World News, and from BBC.com, provided by BBC Global News Ltd.\n" +
                "\n" +
                "From its inception, through the Second World War (where its broadcasts helped to unite the nation), to the 21st century, the BBC has played a prominent role in British culture. It is also known colloquially as \"The Beeb\", \"Auntie\", or a combination of both (as \"Auntie Beeb\" or \"Auntie B\").";
        String s2 = "Denmark (Danish: Danmark, pronounced [ˈdanmɑɡ] (About this soundlisten)), officially the Kingdom of Denmark,[N 9][N 2] is a Nordic country and the southernmost of the Scandinavian nations. Denmark lies southwest of Sweden and south of Norway,[N 10] and is bordered to the south by Germany. The Kingdom of Denmark also comprises two autonomous constituent countries in the North Atlantic Ocean: the Faroe Islands and Greenland. Denmark proper consists of a peninsula, Jutland, and an archipelago of 443 named islands,[N 2][10] with the largest being Zealand, Funen and the North Jutlandic Island. The islands are characterised by flat, arable land and sandy coasts, low elevation and a temperate climate. Denmark has a total area of 42,924 km2 (16,573 sq mi), land area of 42,394 km2 (16,368 sq mi),[3] and the total area including Greenland and the Faroe Islands is 2,210,579 km2 (853,509 sq mi), and a population of 5.8 million (as of 2018).[11]\n" +
                "\n" +
                "The unified kingdom of Denmark emerged in the 10th century as a proficient seafaring nation in the struggle for control of the Baltic Sea.[2] Denmark, Sweden, and Norway were ruled together under one sovereign ruler in the Kalmar Union, established in 1397 and ending with Swedish secession in 1523. The areas of Denmark and Norway remained under the same monarch until 1814, Denmark–Norway. Beginning in the 17th century, there were several devastating wars with the Swedish Empire, ending with large cessions of territory to Sweden. After the Napoleonic Wars, Norway was ceded to Sweden, while Denmark kept the Faroe Islands, Greenland, and Iceland. In the 19th century there was a surge of nationalist movements, which were defeated in the 1864 Second Schleswig War. Denmark remained neutral during World War I. In April 1940, a German invasion saw brief military skirmishes while the Danish resistance movement was active from 1943 until the German surrender in May 1945. An industrialised exporter of agricultural produce in the second half of the 19th century, Denmark introduced social and labour-market reforms in the early 20th century that created the basis for the present welfare state model with a highly developed mixed economy.\n" +
                "\n" +
                "The Constitution of Denmark was signed on 5 June 1849, ending the absolute monarchy, which had begun in 1660. It establishes a constitutional monarchy organised as a parliamentary democracy. The government and national parliament are seated in Copenhagen, the nation's capital, largest city, and main commercial centre. Denmark exercises hegemonic influence in the Danish Realm, devolving powers to handle internal affairs. Home rule was established in the Faroe Islands in 1948; in Greenland home rule was established in 1979 and further autonomy in 2009. Denmark became a member of the European Economic Community (now the EU) in 1973, but negotiated certain opt-outs; it retains its own currency, the krone. It is among the founding members of NATO, the Nordic Council, the OECD, OSCE, and the United Nations; it is also part of the Schengen Area.\n" +
                "\n" +
                "Denmark is considered to be one of the most economically and socially developed countries in the world.[12] Danes enjoy a high standard of living and the country ranks highly in some metrics of national performance, including education, health care, protection of civil liberties, democratic governance, prosperity, and human development.[13][14][15] The country ranks as having the world's highest social mobility,[16] a high level of income equality,[17] is among the countries with the lowest perceived levels of corruption in the world, the eleventh-most developed in the world, has one of the world's highest per capita incomes, and one of the world's highest personal income tax rates.[18]";
        long nano = System.nanoTime();
        Iterable<Character>[] result = test(s1, s2);
        long diff = System.nanoTime() - nano;
        Log.i(result[0].toString());
        Log.i(result[1].toString());
        Log.i("test consumed: " + diff / 1000.0 + " x 10e-6 s.");

        long nano2 = System.nanoTime();
        Iterable<Character>[] result2 = test2(s1, s2);
        long diff2 = System.nanoTime() - nano2;
        Log.i(result2[0].toString());
        Log.i(result2[1].toString());
        Log.i("test2 consumed: " + diff2 / 1000.0 + " x 10e-6 s.");
    }

    private Iterable<Character>[] test2(String string1, String string2) {
        Set<Character> charList1 = new HashSet<>();
        Set<Character> charList2 = new HashSet<>();

        char[] charArrayFromString1 = string1.toCharArray();
        char[] charArrayFromString2 = string2.toCharArray();

        int[] array = new int[Character.MAX_VALUE];
        for (char c : charArrayFromString1) {
            array[c] = 1;
        }
        for (char c : charArrayFromString2) {
            array[c] += 1;
        }

        for (char c : charArrayFromString1) {
            if (array[c] == 1) {
                charList1.add(c);
            }
        }

        // str2
        int[] array2 = new int[Character.MAX_VALUE];
        for (char c : charArrayFromString2) {
            array2[c] = 1;
        }
        for (char c : charArrayFromString1) {
            array2[c] += 1;
        }

        for (char c : charArrayFromString2) {
            if (array2[c] == 1) {
                charList2.add(c);
            }
        }

        return new Set[]{charList1, charList2};
    }

    private Iterable<Character>[] test(String string1, String string2) {
        Set<Character> charList1 = new HashSet<>();
        Set<Character> charList2 = new HashSet<>();

        char[] charArrayFromString1 = string1.toCharArray();
        char[] charArrayFromString2 = string2.toCharArray();

        HashSet<Character> hashSet1 = new HashSet<>();
        HashSet<Character> hashSet2 = new HashSet<>();
        for (char c1 : charArrayFromString1) {
            hashSet1.add(c1);
        }

        for (char c2 : charArrayFromString2) {
            hashSet2.add(c2);
        }

        for (char c1 : charArrayFromString1) {
            if (!hashSet2.contains(c1)) {
                charList1.add(c1);
            }
        }

        for (char c2 : charArrayFromString2) {
            if (!hashSet1.contains(c2)) {
                charList2.add(c2);
            }
        }

        return new Set[]{charList1, charList2};
    }
}
