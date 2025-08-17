

class OrientedGraph:

    def __init__(self, filename: str):

        self._dict_in = {}
        self._dict_out = {}
        self._dict_costs = {}

        self._filename = filename
        self._load_file()

    def _load_file(self):

        lines = []

        try:

            fin = open(self._filename, "rt")

            lines = fin.readlines()

            fin.close()

        except IOError:
            pass

        if len(lines) == 0:
            return
        first_line = lines[0]

        current_line = first_line.split(" ")

        nr_edges = int(current_line[1])
        nr_vertices = int(current_line[0])

        for vertex in range(0, nr_vertices):
            self.add_vertex(vertex)

        for index in range(1, nr_edges + 1):

            line = lines[index].split(" ")
            vertex = int(line[0])
            direction = int(line[1])
            cost = int(line[2])

            self.add_edge(vertex, direction, cost)

    def _save_file(self):

        """
        Saves to the text file all the clients from memory
        :return:
        """

        fout = open(self._filename, "wt")

        nr_vertexes = len(self._dict_in.keys())

        nr_edges = len(self._dict_costs)

        first_line = str(nr_vertexes) + " " + str(nr_edges) + "\n"
        fout.write(first_line)

        for key in self._dict_costs.keys():

            str_line = str(key[0]) + " " + str(key[1]) + " " + str(self._dict_costs[key]) + "\n"

            fout.write(str_line)

        fout.close()

    def clear_file(self):
        """
        Deletes all existing clients from the text file
        :return:
        """

        fin = open(self._filename, "wt")

        fin.truncate(0)

        fin.close()

    def add_edge(self, vertex, direction, cost):

        self._dict_out[vertex].append(direction)

        self._dict_in[direction].append(vertex)

        self._dict_costs[(vertex, direction)] = cost

        self._save_file()

    def search_edge(self, vertex: int, direction: int):

        if (vertex, direction) in self._dict_costs.keys():
            return True
        return False

    def retrieve_info(self, vertex: int, direction: int):

        if (vertex, direction) not in self._dict_costs:
            raise ValueError("The edge does not exist.")
        else:
            return self._dict_costs[(vertex, direction)]

    def search_vertex(self, vertex: int):

        if vertex in self._dict_out.keys():
            return True

        return False

    def add_vertex(self, vertex: int):

        self._dict_out[vertex] = []
        self._dict_in[vertex] = []

        self._save_file()

    def delete_edge(self, vertex: int, direction: int):

        if (vertex, direction) in self._dict_costs:
            del self._dict_costs[(vertex, direction)]
        else:
            raise ValueError("The edge does not exist.")

        # self.clear_file()
        self._save_file()

    def remove_vertex(self, vertex: int):

        copy_costs = self._dict_costs.copy()
        for edge in copy_costs:
            if edge[0] == vertex or edge[1] == vertex:
                self._dict_costs.pop(edge)

        if vertex in self._dict_out:
            self._dict_out.pop(vertex)

        if vertex in self._dict_in:
            self._dict_in.pop(vertex)

        copy_in = self._dict_in.copy()

        for current in copy_in.keys():
            values = copy_in[current]
            for value in values:
                if vertex == value:
                    self._dict_in[current].remove(vertex)

        #self.clear_file()
        self._save_file()

    def get_nr_of_vertices(self):
        return len(self._dict_in.keys())

    def copy_graph(self):

        fin = open("copy_graph.txt", "wt")

        fin.truncate(0)

        fin.close()

        copy_graph = OrientedGraph("copy_graph.txt")

        nr_vertexes = self.get_nr_of_vertices()

        for i in range(0, nr_vertexes):
            copy_graph.add_vertex(i)

        for edge in self._dict_costs:
            copy_graph.add_edge(edge[0], edge[1], self._dict_costs[edge])

        return copy_graph

    def save_file(self):
        self.save_file()

    def nr_of_edges(self):

        return len(self._dict_costs.keys())

    """
    Merges the dictionary of the oriented graph such that an unoriented graph is created
    """
    def merge_dicts(self):

        merged_graph = {}

        for node in self._dict_in.keys():

            edges = self._dict_in[node].copy()
            for edge in self._dict_out[node]:
                if edge not in edges:
                    edges.append(edge)

            merged_graph[node] = edges.copy()

        return merged_graph

    def DFS(self, merged_graph, visited, node, component):

        # We visit the current node, so will change the status from the visited dict to True
        visited[node] = True

        # We add the node to the current connected component of the graph
        component.append(node)

        # The we iterate trough every neighbor of the current node
        for neighbor in merged_graph[node]:

            # If the neighbor was not visited we call the DFS search for the neighbor, until we reach a block
            # when a block is reached the algorithm will recursevely perform the same steps for every neighbor
            # until all the nodes from the current connected component are visited
            if not visited[neighbor]:
                self.DFS(merged_graph, visited, neighbor, component)

    def connected_components(self):

        # Merges the dict_in and dict_out dictionaries such that an undirected graph is formed
        merged_graph = self.merge_dicts()

        # Initialise a dict such that the keys are the nodes from the graph and the value is False
        # We do that such that in DFS method the value of a node will be changed from False to True if
        # the corresponding node was visited or not
        visited = {node: False for node in merged_graph}

        # A list containing all the connected components from the graph
        components = []

        # Iterate trough every node of the graph
        for node in merged_graph:

            # If the node was not visited, then the method DFS responsible for the search of the connected component
            # starting in that respective node will be called

            if not visited[node]:

                # This list represent the connected component that will be found
                component = []

                self.DFS(merged_graph, visited, node, component)

                # After the completion of the DFS method call, the component list will retain the connected component
                # of the graph starting in the current iterated node
                # The component will be appended to the list with the total components found
                components.append(component)

        # The list containing every connected component from the graph will be returned
        return components


    @property
    def get_dict_in(self):
        return self._dict_in

    @property
    def get_dict_out(self):
        return self._dict_out

    @property
    def get_dict_costs(self):
        return self._dict_costs
