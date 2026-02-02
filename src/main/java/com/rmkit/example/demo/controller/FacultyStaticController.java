package com.rmkit.example.demo.controller;

import com.rmkit.example.demo.model.StaffView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class FacultyStaticController {

    @GetMapping("/faculty")
    public String faculty(Model model) {
        List<StaffView> staff = getFaculty(); // hard-coded list below

        // Section order like HOD/Prof/Assoc/Asst
        List<String> order = List.of(
                "Professor & Head",
                "Professor & Dean",
                "Associate Professor (Gr I)",
                "Associate Professor (Gr II)",
                "Associate Professor",
                "Assistant Professor Grade-II",
                "Assistant Professor (Gr II)",
                "Assistant Professor",
                "Associate Professor  Gr (I)" // exact text appears in source; keep to group it
        );

        Map<String, Integer> weight = new LinkedHashMap<>();
        for (int i = 0; i < order.size(); i++) weight.put(order.get(i), i);

        List<StaffView> sorted = staff.stream()
                .sorted(Comparator
                        .comparingInt((StaffView s) -> weight.getOrDefault(s.getDesignation(), 999))
                        .thenComparing(StaffView::getFullName, String.CASE_INSENSITIVE_ORDER))
                .toList();

        Map<String, List<StaffView>> sections = sorted.stream()
                .collect(Collectors.groupingBy(
                        StaffView::getDesignation,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        model.addAttribute("sections", sections);
        model.addAttribute("totalStaff", staff.size());
        return "faculty";
    }

    private List<StaffView> getFaculty() {
        List<StaffView> list = new ArrayList<>();

        // All names/designations/emails taken from:
        // https://www.rmkec.ac.in/2023/infotech_eng/faculty/  (IT Department)  [Source]  [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)
        // Place photos under: src/main/resources/static/images/faculty/
        // Then reference them as: "/images/faculty/<filename>.jpg|.png"
        // If you don't have a photo yet, keep the path and add the file later.

        // ---- HOD / Professors ----
        list.add(new StaffView("Dr. M. Sheerin Banu", "Professor & Head",
                "Information Technology", "hod.it@rmkec.ac.in",
                "/images/faculty/sheerin-banu.png")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Dr. K. Manivannan", "Professor & Dean",
                "Information Technology", null,
                "/images/faculty/k-manivannan.png")); // email not shown in snippet; keep null if unknown  [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Dr. K. Saravanan", "Professor",
                "Information Technology", "ksn.it@rmkec.ac.in",
                "/images/faculty/k-saravanan.png")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Dr. G. S. Anandha Mala", "Professor",
                "Information Technology", "gsa.it@rmkec.ac.in",
                "/images/faculty/gs-anandha-mala.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Dr. Abitha Kumari", "Professor",
                "Information Technology", "dak.it@rmkec.ac.in",
                "/images/faculty/abitha-kumari.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        // ---- Associate Professors ----
        list.add(new StaffView("Dr. S. Selvakanmani", "Associate Professor",
                "Information Technology", "ssk.it@rmkec.ac.in",
                "/images/faculty/s-selvakanmani.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Dr. A. Vijayaraj", "Associate Professor",
                "Information Technology", "avj.it@rmkec.ac.in",
                "/images/faculty/a-vijayaraj.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Dr. A. Anna Lakshmi", "Associate Professor",
                "Information Technology", "aal.it@rmkec.ac.in",
                "/images/faculty/a-anna-lakshmi.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Dr. R. Rajitha Jasmine", "Associate Professor",
                "Information Technology", "rrj.it@rmkec.ac.in",
                "/images/faculty/r-rajitha-jasmine.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Dr. T. Mahalingam", "Associate Professor",
                "Information Technology", "tmm.it@rmkec.ac.in",
                "/images/faculty/t-mahalingam.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Dr. M. Shanthi", "Associate Professor",
                "Information Technology", null,
                "/images/faculty/m-shanthi.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        // ---- Assistant Professor Grade-II / Associate Prof (Gr I) / Assistant Professors ----
        list.add(new StaffView("Dr. R. Poornima", "Assistant Professor Grade-II",
                "Information Technology", "rpa.it@rmkec.ac.in",
                "/images/faculty/r-poornima.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Dr. A. Anu Monisha", "Associate Professor Grade-II",
                "Information Technology", null,
                "/images/faculty/a-anu-monisha.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Mr. L. Vinoth Kumar", "Associate Professor  Gr (I)",
                "Information Technology", "vk.it@rmkec.ac.in",
                "/images/faculty/l-vinoth-kumar.jpg")); // Note: spacing as appears in source  [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Mr. N. Shanmugam", "Assistant Professor",
                "Information Technology", "shan.cc@rmkec.ac.in",
                "/images/faculty/n-shanmugam.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Ms. M. Rekha", "Assistant Professor",
                "Information Technology", "mra.it@rmkec.ac.in",
                "/images/faculty/m-rekha.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Ms. M. Kanniga Parameshwari", "Assistant Professor",
                "Information Technology", "mkp.it@rmkec.ac.in",
                "/images/faculty/m-kanniga-parameshwari.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Ms. S. Shobana", "Associate Professor  Gr (I)",
                "Information Technology", "ssh.it@rmkec.ac.in",
                "/images/faculty/s-shobana.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Ms. K. Selvi", "Assistant Professor",
                "Information Technology", "ksi.it@rmkec.ac.in",
                "/images/faculty/k-selvi.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Ms. J. Divya", "Assistant Professor Grade-II",
                "Information Technology", "daj.csd@rmkec.ac.in",
                "/images/faculty/j-divya.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Ms. M. Sindhu", "Assistant Professor",
                "Information Technology", null,
                "/images/faculty/m-sindhu.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Ms. S. Nandhini", "Assistant Professor",
                "Information Technology", "sni.it@rmkec.ac.in",
                "/images/faculty/s-nandhini.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Ms. T. Akila", "Assistant Professor",
                "Information Technology", "taa.it@rmkec.ac.in",
                "/images/faculty/t-akila.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Mr. S. Nagarajan", "Assistant Professor",
                "Information Technology", "snn.it@rmkec.ac.in",
                "/images/faculty/s-nagarajan.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        list.add(new StaffView("Ms. R. Renugadevi", "Assistant Professor",
                "Information Technology", "rri.it@rmkec.ac.in",
                "/images/faculty/r-renugadevi.jpg")); // [1](https://www.rmkec.ac.in/2023/infotech_eng/faculty/)

        // NOTE: If the live page contains a few more members not visible in the snippet,
        // just append more entries below in the same pattern.

        return list;
    }
}
