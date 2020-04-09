//author: Rajeev Basanta
import java.util.*;
import java.io.*;

public class MovieGenres {
	
	public static void main(String[] args) throws IOException {  	
       
        File input = new File("movies.csv");
        BufferedReader read= new BufferedReader(new FileReader(input));
        read.readLine(); //skips first line
        String nextLine = read.readLine();
        
        HashMap<String, Integer> genreNum = new HashMap<>();
        HashMap<Integer, Integer> yearNum = new HashMap<>();
        ArrayList<Movie> movies = new ArrayList<>();
        
        //checks if there is a next line
        while(nextLine != null) {
            
            String[] arrLine = nextLine.split(","); //splits the line into sections 
            
            String movieTitle = arrLine[1]; //finds the title at index 1
            
            if(movieTitle.charAt(0) == '\"') {
                int counter = 2;
                while(movieTitle.indexOf("\"") == movieTitle.lastIndexOf("\"")) {
                	movieTitle += arrLine[counter++];
                }
            }
            
            int movieYear = Integer.parseInt(movieTitle.substring(movieTitle.lastIndexOf("("), movieTitle.lastIndexOf(")"))); //splices the movie year from the movie title
            
            movieTitle = movieTitle.substring(0, movieTitle.lastIndexOf("(")).replace("\"", "");  //removes movie year from title           
            
            String genre = arrLine[arrLine.length - 1];
            
            String[] findGenres = genre.split("\\|"); //splits the genres into an array
            
            for(String string : findGenres) { //finds the number of genres and adds it to the hashmap
                genreNum.put(string, genreNum.getOrDefault(string, 0) + 1);
            }
            
            yearNum.put(movieYear, yearNum.getOrDefault(movieYear, 0) + 1); //finds the number of years and adds to the hashmap

            Movie movie = new Movie(movieTitle, movieYear, findGenres);
            movies.add(movie);
            
            nextLine = read.readLine();
        }    
        
        yearNum.keySet().stream().forEach((year) -> {
            System.out.println(yearNum.get(year) + " movies came out in " + year);
        });       
        
        genreNum.keySet().stream().forEach((genre) -> {
            System.out.println(genreNum.get(genre) + " movies under the " + genre + " genre");
        });

    }

}


class Movie {
    
    String movieTitle;
    int year;
    String[] genre;
    
    //creating the Movie object
    public Movie(String movieTitle, int year, String[] genre) {
        this.movieTitle = movieTitle;
        this.year = year;
        this.genre = genre;
    }

    //sets the release year
    public void setReleaseYear(int releaseYear) {
        this.year = releaseYear;
    }
    
    //gets the value for release year
    public int getReleaseYear() {
        return year;
    }

    //sets the genres
    public void setGenres(String[] genre) {
        this.genre = genre;
    }
    
    //returns the string for genre
    public String[] getGenres() {
        return genre;
    }
    
    //sets the title
    public void setTitle(String title) {
        this.movieTitle = title;
    }
    
    //returns the string for title
    public String getTitle() {
        return movieTitle;
    }


}


