from Domain.OrientedGraph import OrientedGraph
from Service.ServiceOrintedGraph import ServiceOrientedGraph
from Service.ServiceOrintedGraph import ServiceOrientedGraph
from Service.ServiceOrintedGraph import create_random_graph

class UserInterface:

    def __init__(self, graph: ServiceOrientedGraph):

        self.graph = graph

    def print_menu(self):

        print("1.Is vertex.")
        print("2.Add vertex.")
        print("3.Is edge.")
        print("4.Add edge.")
        print("5.Retrieve info.")
        print("6.Remove edge.")
        print("7.Remove vertex.")
        print("8.In degree.")
        print("9.Out degree.")
        print("10.Number of vertices.")
        print("11.Copy the graph.")
        print("12.Write graph.")
        print("13.Random graph.")
        print("14.Modify cost.")
        print("15.Parse vertices.")
        print("16.Connected components.")
        print("17.Backwards Djikstra.")
        print("0.Exit")

    def _handle_is_vertex(self):

        try:

            vertex = int(input("Vertex = "))

            if self.graph.is_vertex(vertex):
                print("The vertex exists.\n")
            else:
                print("The vertex does not exist.\n")

        except Exception as ex:
            print("Oooops, ", ex)

    def _handle_add_vertex(self):

        try:

            #vertex = int(input("Vertex = "))

            self.graph.add_vertex()
            print("The vertex was added successfully.")

        except Exception as ex:
            print("Ooops, ", ex)

    def _handle_is_edge(self):

        try:

            source = int(input("Source vertex = "))
            destination = int(input("Destination vertex = "))

            if self.graph.is_edge(source, destination):
                print("The edge exists.\n")
            else:
                print("The edge does not exist.\n")

        except Exception as ex:
            print("Ooops, ", ex)

    def _handle_add_edge(self):

        try:

            source = int(input("Source vertex = "))
            destination = int(input("Destination vertex = "))
            cost = int(input("Cost of the edge = "))

            self.graph.add_edge(source, destination, cost)

            print("The edge was successfully added.")

        except Exception as ex:
            print("Ooops, ", ex)

    def _handle_retrieve_info(self):

        try:

            source = int(input("Source vertex = "))
            destination = int(input("Destination vertex = "))

            cost = self.graph.retrieve_info(source, destination)

            print("The cost of the edge is: ", cost)

        except Exception as ex:
            print("Ooops, ", ex)

    def _handle_remove_edge(self):

        try:

            source = int(input("Source vertex = "))
            destination = int(input("Destination vertex = "))

            self.graph.remove_edge(source, destination)

            print("The edge was removed successfully.")

        except Exception as ex:
            print("Ooops, ", ex)

    def _handle_remove_vertex(self):

        try:

            source = int(input("Vertex = "))

            self.graph.remove_vertex(source)

            print("The vertex was removed successfully.")

        except Exception as ex:
            print("Ooops, ", ex)

    def _handle_in_degree(self):

        try:

            source = int(input("Vertex = "))

            in_degree = self.graph.get_in_degree(source)

            print("The in degree of the vertex ", source, " = ", in_degree)

        except Exception as ex:
            print("Ooops, ", ex)

    def _handle_out_degree(self):

        try:

            source = int(input("Vertex = "))

            out_degree = self.graph.get_out_degree(source)

            print("The out degree of the vertex ", source, " = ", out_degree)

        except Exception as ex:
            print("Ooops, ", ex)

    def _handle_nr_of_vertices(self):

        try:

            nr_vertices = self.graph.nr_of_vertices()

            print("The number of vertices in the graph is: ", nr_vertices)

        except Exception as ex:
            print("Ooops, ", ex)

    def _handle_copy_graph(self):

        try:

            graph = self.graph.copy_graph()

            print("The graph was copied, the file containing the copy is in copy_graph.txt.")

        except Exception as ex:
            print("Ooops, ", ex)

    def _handle_write_graph(self):

        print(self.graph.string_graph())

    def run_ui(self):

        while True:

            self.print_menu()

            option = input("Enter you option: ")

            if option == "1":

                self._handle_is_vertex()

            elif option == "2":

                self._handle_add_vertex()

            elif option == "3":

                self._handle_is_edge()

            elif option == "4":

                self._handle_add_edge()

            elif option == "5":

                self._handle_retrieve_info()

            elif option == "6":
                self._handle_remove_edge()

            elif option == "7":
                self._handle_remove_vertex()

            elif option == "8":
                self._handle_in_degree()

            elif option == "9":
                self._handle_out_degree()

            elif option == "10":
                self._handle_nr_of_vertices()

            elif option == "11":
                self._handle_copy_graph()

            elif option == "12":
                self._handle_write_graph()

            elif option == "13":

                filename = input("Filename = ")
                nr_vertices = int(input("Nr vertices= "))
                nr_edges = int(input("Nr edges= "))

                graph = OrientedGraph(filename)
                create_random_graph(graph, nr_vertices, nr_edges)

            elif option == "14":
                try:

                    source = int(input("Source vertex = "))
                    destination = int(input("Destination vertex = "))
                    cost = int(input("Cost = "))

                    if self.graph.modify_cost(source, destination, cost):
                        print("The edge exists.\n")
                    else:
                        print("The edge does not exist.\n")

                except Exception as ex:
                    print("Ooops, ", ex)

            elif option == "15":
                try:

                    print(self.graph.parse_vertices())

                except Exception as ex:
                    print("Ooops, ", ex)

            elif option == "16":
                components = self.graph.connected_components()
                for component in components:
                    print("Component: ", component)

            elif option == "17":

                start = int(input("Start vertex: "))
                end = int(input("End vertex: "))

                ok, path, cost = self.graph.dijkstra(start, end)

                if ok:

                    string_result = ""

                    for i in range(len(path)):
                        string_result += str(path[i])
                        if i < len(path) - 1:
                            string_result += "->"
                    print(string_result)
                    print("The cost is: ", cost)
                else:
                    print("There is no path between", start, "and", end)

            elif option == "0":

                return

            else:
                print("Invalid option, please retry.\n")
