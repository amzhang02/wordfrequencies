import java.util.ArrayList;
import java.util.List;

public class MergeSorter<E> {

    List<E> merge(List<E> sortedFirstHalf, List<E> sortedSecondHalf){
        List<E> newData = new ArrayList<>();
        int firstListIndex = 0;
        int secondListIndex = 0;
        while(firstListIndex < sortedFirstHalf.size() && secondListIndex < sortedSecondHalf.size()){
            E firstListElement = sortedFirstHalf.get(firstListIndex);
            E secondListElement = sortedSecondHalf.get(secondListIndex);
            if(((Comparable) firstListElement).compareTo(secondListElement) <= 0){
                newData.add(firstListElement);
                firstListIndex++; //we're growin', boys!!!
            }
            else{
                newData.add(secondListElement);
                secondListIndex++;
            }
        }
        newData.addAll(sortedFirstHalf.subList(firstListIndex, sortedFirstHalf.size()));
        newData.addAll(sortedSecondHalf.subList(secondListIndex, sortedSecondHalf.size()));
        return newData;
    }

    public List<E> mergeSort(List<E> data) {
        if (data.size() >= 2){
            List<E> firstHalf = new ArrayList<>();
            List<E> secondHalf = new ArrayList<>();
            //splits list in half
            for (int startingPlace = 0; startingPlace < data.size()/2; startingPlace ++){
                firstHalf.add(data.get(startingPlace));
            }

            for (int startingPlace2 = (data.size()/2); startingPlace2 < data.size(); startingPlace2 ++){
                secondHalf.add(data.get(startingPlace2));
            }

            List<E> sortedFirstHalf = mergeSort(firstHalf);
            List<E> sortedSecondHalf = mergeSort(secondHalf);

            data = merge(sortedFirstHalf, sortedSecondHalf);
        }

        return data;
    }
}
