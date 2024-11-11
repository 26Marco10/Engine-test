package com.onetag.javadev;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class OscarExtractor implements Extractor{
    String femalePath = new File("src/main/resources/oscar_age_female.csv").getAbsolutePath();
    String malePath = new File("src/main/resources/oscar_age_male.csv").getAbsolutePath();

    List<Integer> ageList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();
    List<Integer> countList = new ArrayList<>();

    public void OscarCount(String filePath){
        boolean isFirstLine = true;

        try{
            BufferedReader CSVReader = new BufferedReader(new FileReader(filePath));
            String line;

            while((line = CSVReader.readLine()) != null){
                if(isFirstLine){
                    isFirstLine = false;
                    continue;
                }
                
                String[] columns = line.split(",");
                String name = columns[3].replace('"',' ').trim();
                String age = columns[2];

                if(nameList.contains(name)){
                    int index = nameList.indexOf(name);
                    countList.set(index, countList.get(index) + 1);
                }else{
                    nameList.add(name);
                    ageList.add(Integer.parseInt(age));
                    countList.add(1);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        for(int i = 0; i < countList.size(); i++){
            if(countList.get(i) == 1){
                countList.remove(i);
                nameList.remove(i);
                ageList.remove(i);
            }
        }
    }

    public void OrderList(){
        for(int i = 0; i < countList.size(); i++){
            for(int j = i + 1; j < countList.size(); j++){
                if(countList.get(i) < countList.get(j)){
                    int tempCount = countList.get(i);
                    countList.set(i, countList.get(j));
                    countList.set(j, tempCount);

                    String tempName = nameList.get(i);
                    nameList.set(i, nameList.get(j));
                    nameList.set(j, tempName);

                    int tempAge = ageList.get(i);
                    ageList.set(i, ageList.get(j));
                    ageList.set(j, tempAge);
                }
                if(countList.get(i) == countList.get(j)){
                    if(ageList.get(i) > ageList.get(j)){
                        int tempCount = countList.get(i);
                        countList.set(i, countList.get(j));
                        countList.set(j, tempCount);
    
                        String tempName = nameList.get(i);
                        nameList.set(i, nameList.get(j));
                        nameList.set(j, tempName);
    
                        int tempAge = ageList.get(i);
                        ageList.set(i, ageList.get(j));
                        ageList.set(j, tempAge);
                    }
                }
            }
        }
    }

    @Override
    public List<String> extract() {
        OscarCount(femalePath);
        OscarCount(malePath);
        OrderList();
        return nameList;
    }
    
}
