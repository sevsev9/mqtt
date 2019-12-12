<template>
    <v-container fluid fill-height>
        <v-layout align-center wrap>
            <v-flex xs12 class="mb-5">
                <d3-bar-chart :app-name="appName" :d3-data="d3Data">
                        <!-- Oops, vuetify.js -->
                </d3-bar-chart>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import * as d3 from 'd3';

    export default {
        name: 'Chart.vue',
        props: ['oildata'],
        data() {
            return {
                chart: null
            };
        },
        watch: {
            oildata(val) {
                if (this.chart != null) this.chart.remove();
                this.renderChart(val);
            }
        },
        methods: {
            renderChart(oildata_val) {
                const margin = 60;
                const svg_width = 1000;
                const svg_height = 600;
                const chart_width = 1000 - 2 * margin;
                const chart_height = 600 - 2 * margin;

                const svg = d3
                    .select("svg")
                    .attr("width", svg_width)
                    .attr("height", svg_height);

                this.chart = svg
                    .append("g")
                    .attr("transform", `translate(${margin}, ${margin})`);

                const yScale = d3
                    .scaleLinear()
                    .range([chart_height, 0])
                    .domain([0, _.maxBy(oildata_val, "oildata").oildata]);

                this.chart
                    .append("g")
                    .call(d3.axisLeft(yScale).ticks(_.maxBy(oildata_val, "oildata").oildata));

                const xScale = d3
                    .scaleBand()
                    .range([0, chart_width])
                    .domain(oildata_val.map(s => s.day))
                    .padding(0.2);

                this.chart
                    .append("g")
                    .attr("transform", `translate(0, ${chart_height})`)
                    .call(d3.axisBottom(xScale));

                const barGroups = this.chart
                    .selectAll("rect")
                    .data(oildata_val)
                    .enter();

                barGroups
                    .append("rect")
                    .attr("class", "bar")
                    .attr("x", g => xScale(g.day))
                    .attr("y", g => yScale(g.oildata))
                    .attr("height", g => chart_height - yScale(g.oildata))
                    .attr("width", xScale.bandwidth());
            }
        }
    }
</script>

<style scoped>

</style>