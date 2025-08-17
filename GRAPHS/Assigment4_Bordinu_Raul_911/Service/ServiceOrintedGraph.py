from random import randint

from Domain.OrientedGraph import OrientedGraph


def edge_exist(edges, x, y):

    for edge in edges:
        if edge[0] == x and edge[1] == y:
            return True

    return False


def generate_random_edges(nr_vertex: int, nr_edges):

    counter = nr_edges
    edges = []
    while counter:

        x = randint(0, nr_vertex - 1)
        y = randint(0, nr_vertex - 1)
        cost = randint(0, 25)

        if not edge_exist(edges, x, y):
            edges.append((x, y, cost))
            counter -= 1

    return edges


def create_random_graph(graph, nr_vertexes, nr_edges):

    edges = generate_random_edges(nr_vertexes, nr_edges)

    for vertex in range(0, nr_vertexes):
        graph.add_vertex(vertex)

    for edge in edges:
        graph.add_edge(edge[0], edge[1], edge[2])


class ServiceOrientedGraph:

    def __init__(self, graph: OrientedGraph):

        self.graph = graph

    def is_vertex(self, vertex: int):

        return self.graph.search_vertex(vertex)

    def add_vertex(self):

        vertex_number = self.graph.get_nr_of_vertices()
        self.graph.add_vertex(vertex_number)

    def is_edge(self, vertex: int, direction: int):

        return self.graph.search_edge(vertex, direction)

    def add_edge(self, vertex: int, direction: int, cost: int):

        return self.graph.add_edge(vertex, direction, cost)

    def retrieve_info(self, vertex: int, direction: int):

        if self.is_edge(vertex, direction):
            return self.graph.retrieve_info(vertex, direction)

    def remove_edge(self, vertex: int, direction: int):

        self.graph.delete_edge(vertex, direction)

    def remove_vertex(self, vertex: int):

        self.graph.remove_vertex(vertex)

    def nr_of_vertices(self):
        return self.graph.get_nr_of_vertices()

    def get_in_degree(self, vertex: int):

        if vertex in self.graph.get_dict_in.keys():

            return len(self.graph.get_dict_in[vertex])

        else:
            raise ValueError("The vertex does not exist.\n")

    def get_out_degree(self, vertex: int):

        if vertex in self.graph.get_dict_out.keys():
            return len(self.graph.get_dict_out[vertex])
        else:
            raise ValueError("The vertex does not exist.\n")

    def copy_graph(self):

        return self.graph.copy_graph()

    def string_graph(self):

        str_graph = ""

        edges = self.graph.get_dict_costs
        nr_vertices = str(self.graph.get_nr_of_vertices())
        nr_edges = str(self.graph.nr_of_edges())

        str_graph += nr_vertices + " " + nr_edges + "\n"

        for edge in edges:

            str_graph += str(edge[0]) + " " + str(edge[1]) + " " + str(edges[edge]) + "\n"

        return str_graph

    def save_file(self):
        self.graph.save_file()

    def modify_cost(self, vertex, direction, cost):

        if (vertex, direction) not in self.graph.get_dict_costs.keys():
            raise ValueError("Edge not in graph")

        self.graph.get_dict_costs[vertex, direction] = cost

    def parse_vertices(self):

        vertices = ""

        for vertix in self.graph.get_dict_out.keys():
            vertices += str(vertix) + "\n"

        return vertices

    def connected_components(self):

        return self.graph.connected_components()

    def dijkstra(self, start, end):

        return self.graph.dijkstra(start, end)

    def topological_sort(self):

        return self.graph.topological_sort()

    def highest_cost_path(self, start, end):

        return self.graph.highest_cost_path(start, end)
