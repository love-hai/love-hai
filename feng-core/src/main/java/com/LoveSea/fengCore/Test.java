package com.LoveSea.fengCore;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Date;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {
    public static void main(String[] args) throws Exception {
        String content = "ssr://djEtbWl4aW5nLnBvbGdhZGUuY29tOjEwMDY6YXV0aF9hZXMxMjhfbWQ1OmFlcy0yNTYtY2ZiOmh0dHBfc2ltcGxlOlNGVkJTRVU9Lz9vYmZzcGFyYW09Wkc5M2JteHZZV1F1YldsamNtOXpiMlowTG1OdmJRJnByb3RvcGFyYW09TVRBMk9UZzFPamxwUjBaR1pBJnJlbWFya3M9TU9XY3NPV2RnRG9nSUdoMVlXaGxMbTF2WlEmZ3JvdXA9VkdWemRB\n" +
                "ssr://djEtbWl4aW5nLnBvbGdhZGUuY29tOjEwMDY6YXV0aF9hZXMxMjhfbWQ1OmFlcy0yNTYtY2ZiOmh0dHBfc2ltcGxlOlNGVkJTRVU9Lz9vYmZzcGFyYW09Wkc5M2JteHZZV1F1YldsamNtOXpiMlowTG1OdmJRJnByb3RvcGFyYW09TVRBMk9UZzFPamxwUjBaR1pBJnJlbWFya3M9TU9tQ3J1ZXVzVHBvZFdGb1pXTnNiM1ZrUUdkdFlXbHNMbU52YlEmZ3JvdXA9VkdWemRB\n" +
                "ssr://djItbWl4aW5nLmh1YWhleXUuY29tOjIwMzM6YXV0aF9hZXMxMjhfbWQ1OmFlcy0yNTYtY2ZiOmh0dHBfc2ltcGxlOlNGVkJTRVU9Lz9vYmZzcGFyYW09Wkc5M2JteHZZV1F1YldsamNtOXpiMlowTG1OdmJRJnByb3RvcGFyYW09TVRBMk9UZzFPamxwUjBaR1pBJnJlbWFya3M9TVNEb3A0YnBvcEV2NUxpTDZMMjk1TGlUNTVTbzU3cV82TGV2Jmdyb3VwPVZHVnpkQQ\n" +
                "ssr://djItbWl4aW5nLmRuc2x1b3RlLmNvbToxMDE3OmF1dGhfYWVzMTI4X21kNTphZXMtMjU2LWNmYjpodHRwX3NpbXBsZTpTRlZCU0VVPS8_b2Jmc3BhcmFtPVpHOTNibXh2WVdRdWJXbGpjbTl6YjJaMExtTnZiUSZwcm90b3BhcmFtPU1UQTJPVGcxT2pscFIwWkdaQSZyZW1hcmtzPU11bW1tZWE0cncmZ3JvdXA9VkdWemRB\n" +
                "ssr://djItbWl4aW5nLmRuc2x1b3RlLmNvbToxMDA5OmF1dGhfYWVzMTI4X21kNTphZXMtMjU2LWNmYjpodHRwX3NpbXBsZTpTRlZCU0VVPS8_b2Jmc3BhcmFtPVpHOTNibXh2WVdRdWJXbGpjbTl6YjJaMExtTnZiUSZwcm90b3BhcmFtPU1UQTJPVGcxT2pscFIwWkdaQSZyZW1hcmtzPU11bW1tZWE0cnlBeSZncm91cD1WR1Z6ZEE\n" +
                "ssr://djItbWl4aW5nLmRuc2x1b3RlLmNvbToxMDExOmF1dGhfYWVzMTI4X21kNTphZXMtMjU2LWNmYjpodHRwX3NpbXBsZTpTRlZCU0VVPS8_b2Jmc3BhcmFtPVpHOTNibXh2WVdRdWJXbGpjbTl6YjJaMExtTnZiUSZwcm90b3BhcmFtPU1UQTJPVGcxT2pscFIwWkdaQSZyZW1hcmtzPU15RGx2cmZsbTcwJmdyb3VwPVZHVnpkQQ\n" +
                "ssr://djItbWl4aW5nLmRuc2x1b3RlLmNvbToxMDEwOmF1dGhfYWVzMTI4X21kNTphZXMtMjU2LWNmYjpodHRwX3NpbXBsZTpTRlZCU0VVPS8_b2Jmc3BhcmFtPVpHOTNibXh2WVdRdWJXbGpjbTl6YjJaMExtTnZiUSZwcm90b3BhcmFtPU1UQTJPVGcxT2pscFIwWkdaQSZyZW1hcmtzPU15RHBuNm5sbTcwJmdyb3VwPVZHVnpkQQ\n" +
                "ssr://djItbWl4aW5nLmRuc2x1b3RlLmNvbToxMDE0OmF1dGhfYWVzMTI4X21kNTphZXMtMjU2LWNmYjpodHRwX3NpbXBsZTpTRlZCU0VVPS8_b2Jmc3BhcmFtPVpHOTNibXh2WVdRdWJXbGpjbTl6YjJaMExtTnZiUSZwcm90b3BhcmFtPU1UQTJPVGcxT2pscFIwWkdaQSZyZW1hcmtzPU0tV1BzT2E1dmcmZ3JvdXA9VkdWemRB\n" +
                "ssr://djItbWl4aW5nLmRuc2x1b3RlLmNvbToyMDI3OmF1dGhfYWVzMTI4X21kNTphZXMtMjU2LWNmYjpodHRwX3NpbXBsZTpTRlZCU0VVPS8_b2Jmc3BhcmFtPVpHOTNibXh2WVdRdWJXbGpjbTl6YjJaMExtTnZiUSZwcm90b3BhcmFtPU1UQTJPVGcxT2pscFIwWkdaQSZyZW1hcmtzPU0tYVdzT1dLb09XZG9RJmdyb3VwPVZHVnpkQQ\n" +
                "ssr://djEtbWl4aW5nLmh1YWhleXUuY29tOjEwMDU6YXV0aF9hZXMxMjhfbWQ1OmFlcy0yNTYtY2ZiOmh0dHBfc2ltcGxlOlNGVkJTRVU9Lz9vYmZzcGFyYW09Wkc5M2JteHZZV1F1YldsamNtOXpiMlowTG1OdmJRJnByb3RvcGFyYW09TVRBMk9UZzFPamxwUjBaR1pBJnJlbWFya3M9TS1hWHBlYWNyQ0F4Jmdyb3VwPVZHVnpkQQ\n" +
                "ssr://djEtbWl4aW5nLmh1YWhleXUuY29tOjEwMTI6YXV0aF9hZXMxMjhfbWQ1OmFlcy0yNTYtY2ZiOmh0dHBfc2ltcGxlOlNGVkJTRVU9Lz9vYmZzcGFyYW09Wkc5M2JteHZZV1F1YldsamNtOXpiMlowTG1OdmJRJnByb3RvcGFyYW09TVRBMk9UZzFPamxwUjBaR1pBJnJlbWFya3M9TS1lLWp1V2J2USZncm91cD1WR1Z6ZEE\n" +
                "ssr://djEtbWl4aW5nLmh1YWhleXUuY29tOjEwMDI6YXV0aF9hZXMxMjhfbWQ1OmFlcy0yNTYtY2ZiOmh0dHBfc2ltcGxlOlNGVkJTRVU9Lz9vYmZzcGFyYW09Wkc5M2JteHZZV1F1YldsamNtOXpiMlowTG1OdmJRJnByb3RvcGFyYW09TVRBMk9UZzFPamxwUjBaR1pBJnJlbWFya3M9TS1lLWp1V2J2U0F5Jmdyb3VwPVZHVnpkQQ\n" +
                "ssr://dHcucG9sZ2FkZS5jb206MTA1MTphdXRoX2FlczEyOF9tZDU6YWVzLTI1Ni1jZmI6aHR0cF9zaW1wbGU6U0ZWQlNFVT0vP29iZnNwYXJhbT1aRzkzYm14dllXUXViV2xqY205emIyWjBMbU52YlEmcHJvdG9wYXJhbT1NVEEyT1RnMU9qbHBSMFpHWkEmcmVtYXJrcz1OT21kbnVXa3AtbVpodVM0ay1lVXFDM2xqN0RtdWI0Jmdyb3VwPVZHVnpkQQ\n" +
                "ssr://anAucG9sZ2FkZS5jb206MTA1MTphdXRoX2FlczEyOF9tZDU6YWVzLTI1Ni1jZmI6aHR0cF9zaW1wbGU6U0ZWQlNFVT0vP29iZnNwYXJhbT1aRzkzYm14dllXUXViV2xqY205emIyWjBMbU52YlEmcHJvdG9wYXJhbT1NVEEyT1RnMU9qbHBSMFpHWkEmcmVtYXJrcz1OT21kbnVXa3AtbVpodVM0ay1lVXFDM21sNlhtbkt3Jmdyb3VwPVZHVnpkQQ\n" +
                "ssr://dXMucG9sZ2FkZS5jb206MTA1MTphdXRoX2FlczEyOF9tZDU6YWVzLTI1Ni1jZmI6aHR0cF9zaW1wbGU6U0ZWQlNFVT0vP29iZnNwYXJhbT1aRzkzYm14dllXUXViV2xqY205emIyWjBMbU52YlEmcHJvdG9wYXJhbT1NVEEyT1RnMU9qbHBSMFpHWkEmcmVtYXJrcz1OT21kbnVXa3AtbVpodVM0ay1lVXFDM252bzdsbTcwJmdyb3VwPVZHVnpkQQ\n" +
                "ssr://aGsucG9sZ2FkZS5jb206MTA1MTphdXRoX2FlczEyOF9tZDU6YWVzLTI1Ni1jZmI6aHR0cF9zaW1wbGU6U0ZWQlNFVT0vP29iZnNwYXJhbT1aRzkzYm14dllXUXViV2xqY205emIyWjBMbU52YlEmcHJvdG9wYXJhbT1NVEEyT1RnMU9qbHBSMFpHWkEmcmVtYXJrcz1OT21kbnVXa3AtbVpodVM0ay1lVXFDM3BwcG5tdUs4Jmdyb3VwPVZHVnpkQQ\n";
        StringSelection selection = new StringSelection(content);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

}
