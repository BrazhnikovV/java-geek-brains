package jprof.lesson_6;

public class ArrayWorker {

    /**
     * constuctor -
     */
    public ArrayWorker () {

    }

    /**
     * getPartArrayAfterRequiredNumber - получить часть массива
     * полсе указанной последней цифры
     *
     * @param inputArr - входной массив
     * @param lastRequiredNumber - число после которого обрезается массив
     */
    public static int[] getPartArrayAfterRequiredNumber ( int[] inputArr, int lastRequiredNumber ) {

        int lastIndex = 0;

        for ( int i = 0; i < inputArr.length; i++ ) {
            if ( inputArr[i] == lastRequiredNumber ) {
                lastIndex = i;
            }
        }

        // проверяем массив на отсутствие требуемой цифры
        if ( lastIndex == 0 && inputArr[0] != 4 ) {
            throw new RuntimeException();
        }

        // определяем величины возвращаемого массива
        int[] OutputArr = new int[inputArr.length - lastIndex];

        // формируем возвращаемый массив
        System.arraycopy( inputArr, lastIndex, OutputArr, 0, OutputArr.length );

        return OutputArr;
    }

    /**
     * checkArrayFromOneFourNumbers - проверить массив
     * на исключительное наличие единиц и четверок
     *
     * @param inputArr - входной массив
     */
    public static boolean checkArrayFromOneFourNumbers ( int[] inputArr ) {

        for ( int i = 0; i < inputArr.length; i++ ) {
            if ( inputArr[i] != 4 && inputArr[i] != 1 ) {
                return false;
            }
        }

        return true;
    }
}
