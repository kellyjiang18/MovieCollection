import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a tital search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast() {
        System.out.println("Who do you want to search for?");
        String searchTerm = scanner.nextLine();

        searchTerm=searchTerm.toLowerCase();

        ArrayList<String> results = new ArrayList<String>();
        for(int i=0;i<movies.size();i++) {
            String castM = movies.get(i).getCast() + "|";
            castM = castM.toLowerCase();
            while (castM.contains("|")) {
                if (!results.contains(castM.substring(0, castM.indexOf("|") + 1))) {
                    results.add(castM.substring(0, castM.indexOf("|")));
                    castM = castM.substring(castM.indexOf("|") + 1);
                }
                castM = castM.substring(castM.indexOf("|") + 1);
            }
        }

        for (int j = 1; j < results.size(); j++)
        {
            String temp = results.get(j);
            int possibleIndex = j;
            while (possibleIndex > 0 && temp.compareTo(results.get(possibleIndex-1))<0)
            {
                results.set(possibleIndex,results.get(possibleIndex-1));
                possibleIndex--;
            }
            results.set(possibleIndex,temp);
        }
        for(int e=1;e< results.size();e++)
        {
            if(results.get(e-1).indexOf(results.get(e))!=-1)
            {results.remove(e);
            e--;}
        }
        ArrayList<String> temp = new ArrayList<String>();
        for(int a=0;a<results.size();a++)
        {
            if(results.get(a).indexOf(searchTerm)!=-1)
            {temp.add(results.get(a));}
        }
        results=temp;
        for (int i = 0; i < results.size(); i++)
        {
            String cast = results.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + cast);
        }

        System.out.println("Which cast member would you like to learn more about?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        String castM=results.get(choice-1);

        ArrayList<Movie> mResults = new ArrayList<Movie>();
        for(int k=0;k<movies.size();k++)
        {
            if(movies.get(k).getCast().toLowerCase().indexOf(results.get(choice-1))!=-1)
            {mResults.add(movies.get(k));}
        }
        for (int i = 0; i < mResults.size(); i++)
        {
            Movie cast = mResults.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + cast);
        }
        System.out.println("Choose a movie to learn more about: ");
        int choice1 = scanner.nextInt();
        scanner.nextLine();
        displayMovieInfo(mResults.get(choice1-1));

    }

    private void searchKeywords()
    {
        System.out.print("Enter a keyword search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String keyword = movies.get(i).getKeywords();
            keyword = keyword.toLowerCase();

            if (keyword.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listGenres()
    {
        ArrayList<String> results = new ArrayList<String>();
        for(int i=0;i<movies.size();i++) {
            String genres = movies.get(i).getGenres() + "|";
            genres = genres.toLowerCase();
            while (genres.contains("|")) {
                if (!results.contains(genres.substring(0, genres.indexOf("|") + 1))) {
                    results.add(genres.substring(0, genres.indexOf("|")));
                    genres = genres.substring(genres.indexOf("|") + 1);
                }
                genres = genres.substring(genres.indexOf("|") + 1);
            }
        }

        for (int j = 1; j < results.size(); j++)
        {
            String temp = results.get(j);
            int possibleIndex = j;
            while (possibleIndex > 0 && temp.compareTo(results.get(possibleIndex-1))<0)
            {
                results.set(possibleIndex,results.get(possibleIndex-1));
                possibleIndex--;
            }
            results.set(possibleIndex,temp);
        }
        for(int e=1;e< results.size();e++)
        {
            if(results.get(e-1).indexOf(results.get(e))!=-1)
            {results.remove(e);
                e--;}
        }
        for (int i = 0; i < results.size(); i++)
        {
            String genre= results.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + genre);
        }
        System.out.println("What genre are you searching for?");
        int searchTerm = scanner.nextInt();

        ArrayList<Movie> temp = new ArrayList<Movie>();
        for(int a=0;a<movies.size();a++)
        {
            if(movies.get(a).getGenres().toLowerCase().indexOf(results.get(searchTerm-1))!=-1)
            {temp.add(movies.get(a));}
        }
        for (int i = 0; i < temp.size(); i++)
        {
            Movie movie= temp.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + movie.getTitle());
        }

        System.out.println("Which movie?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        displayMovieInfo(temp.get(choice-1));

    }

    private void listHighestRated()
    {
        ArrayList<Movie> list = new ArrayList<Movie>();
        list.add(movies.get(0));
        for(int i=1;i<movies.size()-1;i++)
        {
            for(int o=0;o<list.size();o++)
            {
                if(list.get(o).getUserRating()<movies.get(i).getUserRating())
                {
                    list.add(o,movies.get(i));
                }
            }
        }
        while(list.size()!=50)
        {
            list.remove(51);
        }
        for (int i = 0; i < list.size(); i++)
        {
            Movie movie= list.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + movie.getTitle()+" : "+movie.getUserRating());
        }
        System.out.println("Which movie?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        displayMovieInfo(list.get(choice-1));
    }

    private void listHighestRevenue()
    {
        ArrayList<Movie> list = new ArrayList<Movie>();
        list.add(movies.get(0));
        for(int i=1;i<movies.size()-1;i++)
        {
            for(int o=0;o<list.size();o++)
            {
                if(list.get(o).getUserRating()<movies.get(i).getRevenue())
                {
                    list.add(o,movies.get(i));
                }
            }
        }
        while(list.size()!=50)
        {
            list.remove(51);
        }
        for (int i = 0; i < list.size(); i++)
        {
            Movie movie= list.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + movie.getTitle()+" : "+movie.getRevenue());
        }
        System.out.println("Which movie?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        displayMovieInfo(list.get(choice-1));
    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}
